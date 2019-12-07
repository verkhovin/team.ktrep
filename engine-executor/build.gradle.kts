plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    jcenter()
}

group = "kt.team"
version = "0.1"

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.koin:koin-ktor:2.0.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation ("io.r2dbc:r2dbc-postgresql:0.8.0.RELEASE")
    implementation ("io.r2dbc:r2dbc-pool:0.8.0.RELEASE")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.2")

}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "kt.team.api.NSApp"
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}