package kt.team.dao

import io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.HOST
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PORT
import io.r2dbc.spi.ConnectionFactoryOptions.USER


//БД - 134.209.82.145:5432 teamkt:supersecurepassword
//val connectionFactory = PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
//    .host("134.209.82.145")
//    .port(5432)  // optional, defaults to 5432
//    .username("teamkt")
//    .password("supersecurepassword")
//    .database("teamkt")  // optional
//    .build());

var connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
    .option(DRIVER, "postgresql")
    .option(HOST, "134.209.82.145")
    .option(PORT, 5432)
    .option(USER, "teamkt")
    .option(PASSWORD, "supersecurepassword")
    .option(DATABASE, "teamkt")
    .option(MAX_SIZE, 10)
    .build())