package kt.team.executor

import kotlinx.coroutines.delay
import kt.team.enricher.PlainEnricher
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject

val log = KotlinLogging.logger{}

class Executor : KoinComponent {

    suspend fun startEnrichment(){
        val enricher: PlainEnricher by inject()
        while (true) {
            try {
                enricher.enrich()
            } catch (e: Exception){
                val error = "Enrichment error: ${e.message}"
                println(error)
                log.error(error)
            }
            //delay(50L)
        }
    }
}