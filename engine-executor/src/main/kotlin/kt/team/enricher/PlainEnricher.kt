package kt.team.enricher

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kt.team.dao.ContentDao
import kt.team.dao.UserDao
import kt.team.enricher.calculator.calcImportant
import kt.team.entity.UserContent

class PlainEnricher(
    private val userDao: UserDao,
    private val containerDao: ContentDao
) : Enricher {

    @ExperimentalCoroutinesApi
    override suspend fun enrich() {
        coroutineScope {
            val users = async { userDao.getUsers().toList() }
            val contentList = async { containerDao.findContentList().toList() }.await()
            users.await().map { user  ->
                async {
                    userDao.updateUserContentCorr(
                        user.also {
                            it.contents = contentList.map {content ->
                                UserContent(
                                    content.id,
                                    score = it.calcImportant(content)
                                )
                            }
                        }
                    ).count()
                }
            }.forEach{
                it.await()
            }
        }
    }
}
