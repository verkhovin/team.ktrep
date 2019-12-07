package kt.team.api

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import kt.team.api.configuration.api.configureApi
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import java.text.SimpleDateFormat

class NSApi : KoinComponent {

    fun start() = runBlocking {
        val port = getKoin().getProperty("app.port", 8080)
        embeddedServer(Netty, port) {
            configureServer()
        }.start()
    }

    fun Application.configureServer() {
        install(CallLogging) {
            level = org.slf4j.event.Level.DEBUG
        }
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                registerModule(JodaModule())
                dateFormat = SimpleDateFormat("yyyy-MM-dd")
            }
        }
        configureApi()
    }
}
