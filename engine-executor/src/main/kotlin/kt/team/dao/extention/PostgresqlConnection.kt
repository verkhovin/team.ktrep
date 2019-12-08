package kt.team.dao.extention

import io.r2dbc.postgresql.api.PostgresqlConnection

fun PostgresqlConnection.closeConnection() = this.close().subscribe()