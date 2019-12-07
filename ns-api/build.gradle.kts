plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

apply(plugin = "org.jetbrains.kotlin.kapt")

group = "kt.team"
version = "0.0"

val koinVersion = "2.0.1"
val ktorVersion = "1.2.4"
val requeryVersion = "1.6.1"
val exposedVersion = "0.18.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation ("io.ktor:ktor-jackson:$ktorVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda:2.10.1")


    implementation("org.koin:koin-ktor:$koinVersion")

    implementation("org.postgresql:postgresql:42.2.8.jre7")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")

    implementation("com.zaxxer:HikariCP:3.4.1")
}

kapt {
    generateStubs = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}