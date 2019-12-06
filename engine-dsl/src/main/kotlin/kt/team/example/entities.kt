package kt.team.example

import kt.team.dsl.function
import kt.team.dsl.functions

class User(
    val name: String,
    val age: Int,
    val sex: String,
    val tags: Set<String>
)

class Content(
    val info: String,
    val tags: Set<String>
)

val functions = functions<User, Content>(
    function<User, Content>{
        this.age > 25 && this.sex == "M" && "sport" in it.tags
    },
    function{
        this.age < 18 && "education" in it.tags
    }
)


fun main() {
    val user = User("Gru", 17, "M", setOf("healthy"))
    val content = Content("Football", setOf("education", "sport", "healthy", "food"))
    val value = user.calcImportant(content)
    print(value)
}