package kt.team.api.configuration

import kt.team.api.configuration.properties.JdbcProperties
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import kt.team.api.persist.service.ArticleService
import kt.team.api.persist.service.ContentService
import kt.team.api.persist.service.UserService

val nsApiModule = module {
    single { ArticleService() }
    single { UserService() }
    single { ContentService() }
    val properties = JdbcProperties(
        jdbcurl = get().koin.getProperty<String>("datasource.jdbcurl").orEmpty(),
        username = get().koin.getProperty<String>("datasource.username").orEmpty(),
        password = get().koin.getProperty<String>("datasource.password").orEmpty()
    )
    configurePersistence(properties)
}