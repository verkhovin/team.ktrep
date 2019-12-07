import configuration.nsApiModule
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin {
        fileProperties()
        environmentProperties()
        modules(nsApiModule)
    }

    NSApi().start()
}