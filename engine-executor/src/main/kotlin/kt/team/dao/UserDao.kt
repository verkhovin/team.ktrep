package kt.team.dao

import io.r2dbc.spi.Result
import kotlinx.coroutines.flow.Flow
import kt.team.entity.User

interface UserDao {

    suspend fun getUsers(): Flow<User>

    suspend fun updateUserContentCorr(user: User): Flow<Result>
}
