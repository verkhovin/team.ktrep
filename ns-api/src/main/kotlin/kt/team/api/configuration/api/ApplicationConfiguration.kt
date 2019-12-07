package kt.team.api.configuration.api

import io.ktor.application.Application
import io.ktor.routing.routing

fun Application.configureApi() {
    routing {
        configureFrontApi()
        configureInputApi()
    }
}
