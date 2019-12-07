package kt.team.api.persist.service

import io.swagger.client.models.NewItem
import kt.team.api.model.Item
import kt.team.api.model.Reaction
import kt.team.api.model.User
import kt.team.api.persist.model.ArticleTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import kt.team.api.persist.model.ContentTable
import kt.team.api.persist.model.ContentTable.articleId
import org.jetbrains.exposed.sql.insert
import org.joda.time.DateTime

class ContentService() {
    fun saveContent(newItem: NewItem): Unit  = transaction {
        val articleId = newItem.body?.article?.let {
            ArticleTable.insert {
                it[created] = DateTime.now()
                it[content] = newItem.body.article
            }[ArticleTable.id]
        }
        ContentTable.insert {
            it[type] = newItem.type.toString()
            it[created] = DateTime.now()
            it[text] = newItem.body?.text ?: ""
            it[imageUrl] = newItem.body?.image ?: ""
            it[videoUrl] = newItem.body?.video?: ""
            it[this.articleId] = articleId
        }
    }

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