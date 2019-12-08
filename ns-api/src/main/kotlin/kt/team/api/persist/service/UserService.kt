package kt.team.api.persist.service

import kt.team.api.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import kt.team.api.persist.model.UserTable
import org.jetbrains.exposed.sql.update
import java.util.UUID

class UserService {

    fun getUsersById(id: UUID): User {
        val result = UserTable.select {
            (UserTable.id eq id)
        }.mapNotNull {
            toUser(it)
        }.singleOrNull()
            ?: throw RuntimeException("User $id not found!")
        return result
    }

    fun addProduct(userId: String, product: String) = transaction {
        val user = getUsersById(UUID.fromString(userId))
        UserTable.update({UserTable.id eq UUID.fromString(userId)}) {
            it[products] = user.product + product
        }
    }

    fun addTag(userId: String, tag: String) {
        val user = getUsersById(UUID.fromString(userId))
        UserTable.update({UserTable.id eq UUID.fromString(userId)}) {
            it[products] = user.tags + tag
        }
    }

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