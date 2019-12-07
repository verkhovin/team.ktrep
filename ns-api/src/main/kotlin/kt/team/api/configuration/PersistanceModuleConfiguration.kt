package configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import configuration.properties.JdbcProperties
import org.jetbrains.exposed.sql.Database

fun configurePersistence(properties: JdbcProperties) {
    Database.connect(hikari(properties))
}

fun hikari(properties: JdbcProperties): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = properties.jdbcurl
    config.username = properties.username
    config.password = properties.password
    config.maximumPoolSize = 3
    config.validate()
    return HikariDataSource(config)
}
