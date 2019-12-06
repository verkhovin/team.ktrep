package kt.team.example

fun User.calcImportant(content: Content) =
    functions.calculate(this, content) + this.tags.intersect(content.tags).size.toDouble() / content.tags.size
