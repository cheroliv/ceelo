buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")
        classpath("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.1")
        classpath("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.1")
        classpath("com.github.triplet.gradle:play-publisher:3.7.0")
    }
}
/*=================================================================================*/
plugins {
    kotlin("jvm") version "1.7.20" apply false
    kotlin("android") version "1.7.20" apply false
    kotlin("plugin.serialization") version "1.7.20" apply false
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.3.1" apply false
    idea
}
/*=================================================================================*/
idea.module.excludeDirs.plusAssign(files("node_modules"))
/*=================================================================================*/
tasks.register<Delete>("clean") {
    description = "Delete directory build"
    group = "build"
    doLast { delete(rootProject.buildDir) }
}
/*=================================================================================*/