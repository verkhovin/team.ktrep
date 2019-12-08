package kt.team.api.configuration.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

fun Routing.configureInputApi() {
    post("/api/input-api") {
        val data = call.receive<Data>()
        call.respond(HttpStatusCode.OK, data)
    }
    get("/version") {
        call.respond("v 0.0")
    }
}

data class Data(val text: String, val number: Int)
