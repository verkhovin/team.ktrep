package kt.team.enricher.calculator

import kt.team.dsl.function
import kt.team.dsl.functions
import kt.team.entity.Content
import kt.team.entity.User

private val func = functions<User, Content>(
    function<User, Content>{
        this.sex == "M" && "sport" in it.tags
    },
    function {
        this.sex == "F" && "family" in it.tags
    },
    function {
        this.location == "Тагил" && "fashion" in it.tags
    }
)

fun User.calcImportant(content: Content) =
    func.calculate(this, content) +
        calcintersection(this.tags ?: emptyList(), content.tags)

private fun calcintersection(userTags: List<String>, contentTags: List<String>): Double =
    if(contentTags.isEmpty()) 0.0 else userTags.intersect(contentTags).size.toDouble() / contentTags.size
