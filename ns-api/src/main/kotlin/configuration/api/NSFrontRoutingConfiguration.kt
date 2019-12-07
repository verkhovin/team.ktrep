package configuration.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import model.Item
import model.Tile
import org.koin.ktor.ext.inject
import persist.service.ArticleService

fun Routing.configureFrontApi(articleService: ArticleService) {
//    val articleService = get

    get("/content/preview/{userId}") {
        //TODO: get data from database
        val response = emptyList<Tile>()
        call.respond(HttpStatusCode.OK, response)
    }

    get("/content/feed/{userId}/{tileId}") {
        //TODO: get data from database
        val response = emptyList<Item>()
        call.respond(HttpStatusCode.OK, response)
    }

    get("/content/article/{articleId}") {
        val response = articleService.getAllArticles()
        call.respond(HttpStatusCode.OK, response)
    }
}