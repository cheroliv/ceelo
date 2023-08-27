/*=================================================================================*/

buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        val JACKSON_VERSION = "jackson_version"
        val PUBLISHER_VERSION = "publisher_version"
        val KOTLIN_VERSION = "kotlin_version"
        val NAV_VERSION = "nav_version"


        mapOf(
            "org.jetbrains.kotlin:kotlin-gradle-plugin" to KOTLIN_VERSION,
            "androidx.navigation:navigation-safe-args-gradle-plugin" to NAV_VERSION,
            "com.fasterxml.jackson.module:jackson-module-kotlin" to JACKSON_VERSION,
            "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml" to JACKSON_VERSION,
            "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to JACKSON_VERSION,
            "com.github.triplet.gradle:play-publisher" to PUBLISHER_VERSION,
        ).forEach { classpath("${it.key}:${properties[it.value]}") }
    }
}
/*=================================================================================*/
plugins {
    val android_app_version = "7.2.1"
    val android_lib_version = "7.3.1"
    val kotlin_version = "1.7.20"


    kotlin("jvm") version kotlin_version apply false
    kotlin("android") version kotlin_version apply false
    kotlin("plugin.serialization") version kotlin_version apply false
    id("com.android.application") version android_app_version apply false
    id("com.android.library") version android_lib_version apply false
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
