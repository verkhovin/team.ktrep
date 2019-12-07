package kt.team.executor

import kt.team.config.appModules
import org.koin.core.context.startKoin

suspend fun main() {
    startKoin {
        modules(appModules)
    }
    Executor().startEnrichment()
}