package configuration

import configuration.properties.JdbcProperties
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import persist.service.ArticleService

val nsApiModule = module {
    single { ArticleService() }
/*    val properties = get().koin.getProperty<JdbcProperties>("datasource")
        ?: throw RuntimeException("JdbcProperties are not loaded!")*/
    val properties = JdbcProperties(
        jdbcurl = get().koin.getProperty<String>("datasource.jdbcurl").orEmpty(),
        username = get().koin.getProperty<String>("datasource.username").orEmpty(),
        password = get().koin.getProperty<String>("datasource.password").orEmpty()
    )
    configurePersistence(properties)
}