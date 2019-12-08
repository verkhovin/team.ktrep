package kt.team.api.persist.service

import kt.team.api.model.Item
import kt.team.api.model.Reaction
import kt.team.api.model.User
import kt.team.api.persist.model.ContentTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.sql.ResultSet
import java.util.UUID

class ContentService {

    fun fetchUserContent(user: User): List<Item> = transaction {
        val result = user.content.map { content ->
            ContentTable.select {
                (ContentTable.id eq content.contentId)
            }.mapNotNull { toItem(it, content.score, content.reaction, content.isBookmarked) }
                .singleOrNull()
                ?: throw RuntimeException("Content ${content.contentId} not found!")
        }
        commit()
        result
    }

    fun fetchRelevantContent(contentId: UUID): List<Item> {
        lateinit var first: Item
        var tags: Array<String>? = emptyArray()
        transaction {
            first = ContentTable.select {
                (ContentTable.id eq contentId)
            }.mapNotNull {
                tags = it.getOrNull(ContentTable.tags)
                toItem(it)
            }.singleOrNull()
                ?: throw RuntimeException("Content $contentId not found!")
            commit()
        }
        return listOf(first) + getByTag(first.id, tags)
    }

    fun getByTag(firstId: String, tags: Array<String>?): List<Item> = try {
        if (!tags.isNullOrEmpty()) {
            val query = "select distinct * from content" +
                " where jsonb_exists_any(tags::jsonb, array ['${tags.joinToString("','")}'])" +
                " and id <> '$firstId'" +
                " limit 20;"
            val results = mutableListOf<Item>()
            TransactionManager.currentOrNew(2).exec(query) { rs ->
                while (rs.next()) {
                    results.add(createItem(rs))
                }
            }
            results
        } else emptyList()
    } finally {
        TransactionManager.currentOrNull()?.closeExecutedStatements()
    }

    private fun toItem(
        row: ResultRow,
        score: Double = 0.0,
        reaction: String = "",
        isBookmarked: Boolean = false
    ) = Item(
        id = row[ContentTable.id].toString(),
        type = row[ContentTable.type],
        created = row[ContentTable.created],
        score = score,
        reaction = Reaction(
            action = reaction,
            isBookmarked = isBookmarked
        ),
        body = Item.Body(
            text = row[ContentTable.text],
            image = row[ContentTable.imageUrl],
            videoUrl = row[ContentTable.videoUrl],
            articleId = row[ContentTable.articleId].toString()
        )
    )

    private fun createItem(rs: ResultSet) = Item(
        id = rs.getString(ContentTable.id.name),
        type = rs.getString(ContentTable.type.name),
        created = DateTime(rs.getDate(ContentTable.created.name)),
        score = 0.0,
        reaction = Reaction(action = "", isBookmarked = false),
        body = Item.Body(
            text = rs.getString(ContentTable.text.name),
            image = rs.getString(ContentTable.imageUrl.name),
            videoUrl = rs.getString(ContentTable.videoUrl.name),
            articleId = rs.getString(ContentTable.articleId.name)
        )
    )
}