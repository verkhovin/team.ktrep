import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin {
        fileProperties()
        environmentProperties()
//        modules(/*there should be modules*/)
    }

    NSApi().start()
}