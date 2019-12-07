import configuration.api.configureApi
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import java.text.DateFormat

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
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
        configureApi()
    }
}
