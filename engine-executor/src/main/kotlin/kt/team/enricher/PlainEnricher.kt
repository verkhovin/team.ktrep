package kt.team.enricher

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kt.team.dao.PgContentDao
import kt.team.dao.PgUserDao
import kt.team.entity.Content
import kt.team.entity.User
import kt.team.entity.UserContent

class PlainEnricher(
    private val userDao: PgUserDao,
    private val containerDao: PgContentDao
) : Enricher {

    override suspend fun enrich() {
        coroutineScope {
            val users = async { userDao.getUsers().toList() }
            val contentList = async { containerDao.findContentList().toList() }.await()
            users.await().map { user  ->
                async {
                    userDao.updateUserContentCorr(
                        user.also {
                            it.contents = contentList.map {
                                UserContent(
                                    user.id,
                                    score = calculateContentValue(user, it)
                                )
                            }
                        }
                    ).count()
                }
            }.forEach{
                it.await()
                println("Save")
            }
        }
    }

    private fun calculateContentValue(user: User, content: Content): Double {
        return 0.1
    }
}
