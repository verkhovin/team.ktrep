package kt.team.api.persist.service

import kt.team.api.model.Item
import kt.team.api.model.Reaction
import kt.team.api.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import kt.team.api.persist.model.ContentTable

class ContentService {

    fun fetchUserContent(user: User): List<Item> = transaction {
        val result = user.content.map { content ->
            ContentTable.select {
                (ContentTable.id eq content.contentId)
            }.mapNotNull { toItem(it, content.score, content.reaction, content.isBookmarked) }
                .singleOrNull()
                ?: throw RuntimeException()
        }
        commit()
        result
    }

    private fun toItem(row: ResultRow, score: Double, reaction: String, isBookmarked: Boolean) =
        Item(
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
}