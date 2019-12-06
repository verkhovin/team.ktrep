package kt.team.dsl

interface Functions<U, C> {

    fun calculate(user: U, content: C): Double
}

private class ContentFunctions<U, C>(private val functions: List<Function<U, C>>): Functions<U, C> {

    override fun calculate(user: U, content: C) =
        functions.sumBy { it.calculate(user, content) }.toDouble() / functions.size
}

interface Function<U, C> {

    fun calculate(user: U, content: C): Int
}

private class ContentFunction<U, C>(private val func: U.(C) -> Boolean) : Function<U, C> {

    override fun calculate(user: U, content: C) =
        if (func.invoke(user, content)) 1 else 0
}

fun <U, C> function(func: U.(C) -> Boolean): Function<U, C> = ContentFunction(func)

fun <U, C> functions(vararg func: Function<U, C>): Functions<U, C> = ContentFunctions(func.toList())