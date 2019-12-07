package persist.service

import model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import persist.model.UserTable
import java.util.UUID

class UserService {

    fun getUsersById(id: UUID) = transaction {
        val result = UserTable.select {
            (UserTable.id eq id)
        }.mapNotNull {
            toUser(it)
        }.singleOrNull()
            ?: throw RuntimeException("User $id not found!")
        commit()
        result
    }

    fun updateUser(user: User) {}

    fun toUser(row: ResultRow) = User(
        id = row[UserTable.id].toString(),
        lastLogin = row[UserTable.lastLogin],
        birthDate = row[UserTable.birthDate],
        gender = row[UserTable.gender],
        location = row[UserTable.location],
        product = row[UserTable.products],
        tags = row[UserTable.tags],
        content = row[UserTable.content]
    )
}