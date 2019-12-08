package kt.team.api.configuration.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject
import kt.team.api.persist.service.ArticleService
import kt.team.api.persist.service.ContentService
import kt.team.api.persist.service.UserService
import java.util.UUID

fun Routing.configureFrontApi() {
    val articleService by inject<ArticleService>()
    val userService by inject<UserService>()
    val contentService by inject<ContentService>()

    get("/api/content/preview/{userId}") {
        val id = UUID.fromString(call.parameters["userId"].orEmpty())
        val user = userService.getUsersById(id)
        val response = contentService.fetchUserContent(user).map { it.toTile() }
        call.respond(HttpStatusCode.OK, response)
    }

    get("/api/content/feed/{tileId}") {
        val tileId = UUID.fromString(call.parameters["tileId"].orEmpty())
        val response = contentService.fetchRelevantContent(tileId)
        call.respond(HttpStatusCode.OK, response)
    }

    get("/api/content/article/{articleId}") {
        val id = UUID.fromString(call.parameters["articleId"].orEmpty())
        val response = articleService.getArticle(id)
        call.respond(HttpStatusCode.OK, response)
    }
}