package configuration.api

import io.ktor.application.Application
import io.ktor.routing.routing
import org.koin.ktor.ext.get

fun Application.configureApi() {
    routing {
        configureFrontApi(get())
        configureInputApi()
    }
}
