package persist.service

import model.Article
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID
import persist.model.Article as ArticleEntity

class ArticleService {
    suspend fun getAllArticles(): List<Article> = ArticleEntity.selectAll().forEach(toArticle())

    suspend fun getArticle(id: UUID) {}
    suspend fun save(article: Article) {}
    suspend fun update(article: Article) {}

    private fun toArticle(row: ResultRow): Article =
        Article(
            id = row[ArticleEntity.id].toString(),
            created = row[ArticleEntity.created],//map correctly DateTime to LocalDateTime
            // or change LocalDateTime to DateTime
            content = row[ArticleEntity.content]
        )
}