package kt.team.api.configuration.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import kt.team.api.persist.service.ContentService
import org.koin.ktor.ext.inject

fun Routing.configureInputApi() {
    val contentService by inject<ContentService>()
    post("input-api") {
        TODO()
    }
    get("/version") {
        call.respond("v 0.0")
    }
    post("/content") {
        call.respond(contentService.saveContent(call.receive()))
    }
}