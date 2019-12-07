package kt.team.dao

import kotlinx.coroutines.flow.Flow
import kt.team.entity.Content

interface ContentDao{

    suspend fun findContentList(): Flow<Content>
}