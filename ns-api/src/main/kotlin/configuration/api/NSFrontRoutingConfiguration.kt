package configuration.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import model.Article
import model.Item
import model.Tile

fun Routing.configureFrontApi() {
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
        //TODO: get data from database
        val response = emptyList<Article>()
        call.respond(HttpStatusCode.OK, response)
    }
}