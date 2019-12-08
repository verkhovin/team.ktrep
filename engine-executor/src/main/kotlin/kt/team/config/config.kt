package kt.team.config

import io.r2dbc.pool.PoolingConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import kt.team.dao.PgContentDao
import kt.team.dao.PgUserDao
import kt.team.enricher.PlainEnricher
import org.koin.core.module.Module
import org.koin.dsl.module

fun Module.configureProps(){
    single { Settings() }
}

fun Module.configurePgDao() {
    single { ConnectionFactories.get(ConnectionFactoryOptions.builder()
        .option(ConnectionFactoryOptions.DRIVER, "postgresql")
        .option(ConnectionFactoryOptions.HOST, "134.209.82.145")
        .option(ConnectionFactoryOptions.PORT, 5432)
        .option(ConnectionFactoryOptions.USER, "teamkt")
        .option(ConnectionFactoryOptions.PASSWORD, "supersecurepassword")
        .option(ConnectionFactoryOptions.DATABASE, "teamkt")
        .option(PoolingConnectionFactoryProvider.MAX_SIZE, 10)
        .build()) }
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