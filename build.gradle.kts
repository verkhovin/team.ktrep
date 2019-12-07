plugins {
    base
    java
    kotlin("jvm") version "1.3.61" apply false
}

allprojects {

    group = "kt.team"

    version = "1.0"

    repositories {
        jcenter()
    }
}

dependencies {
    // Make the root project archives kt.team.api.configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}
