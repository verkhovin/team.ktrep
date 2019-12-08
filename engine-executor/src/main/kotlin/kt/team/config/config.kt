package kt.team.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import kt.team.dao.PgContentDao
import kt.team.dao.PgUserDao
import kt.team.enricher.PlainEnricher
import org.koin.core.module.Module
import org.koin.dsl.module

fun Module.configureProps(){
    single { Settings() }
}

fun Module.configurePgDao() {
    single {
         PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .host("134.209.82.145")
            .port(5432)  // optional, defaults to 5432
            .username("teamkt")
            .password("supersecurepassword")
            .database("teamkt")  // optional
            .build());
    }
    single { PgContentDao(get(), get()) }
    single { PgUserDao(get(), get()) }
}

fun Module.configureEnricher() {
    single { PlainEnricher(get<PgUserDao>(), get<PgContentDao>()) }
}

val appModules = module {
    configureProps()
    configurePgDao()
    configureEnricher()
}