buildscript {


    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    fun String.dep(version:String) = "$this${properties[version]}"

    dependencies {
        val JACKSON_VERSION = "jackson_version"
        val PUBLISHER_VERSION = "publisher_version"
        val KOTLIN_VERSION = "kotlin_version"
        val NAV_VERSION = "nav_version"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:".dep(KOTLIN_VERSION))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:".dep(NAV_VERSION))
        classpath("com.fasterxml.jackson.module:jackson-module-kotlin:".dep(JACKSON_VERSION))
        classpath("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:".dep(JACKSON_VERSION))
        classpath("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:".dep(JACKSON_VERSION))
        classpath("com.github.triplet.gradle:play-publisher:".dep(PUBLISHER_VERSION))
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
