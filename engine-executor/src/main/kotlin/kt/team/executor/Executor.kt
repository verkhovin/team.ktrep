package kt.team.executor

import kotlinx.coroutines.delay
import kt.team.enricher.PlainEnricher
import org.koin.core.KoinComponent
import org.koin.core.inject

class Executor() : KoinComponent {

    suspend fun startEnrichment(){
        val enricher: PlainEnricher by inject()
        while (true) {
            println("iteration")
            enricher.enrich()
            delay(5000L)
        }
    }
}