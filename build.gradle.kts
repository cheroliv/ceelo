buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${properties["nav_version"]}")
    }
}

plugins {
    id("com.android.application") version ("7.1.1") apply (false)
    id("com.android.library") version ("7.1.1") apply (false)
    id("org.jetbrains.kotlin.android") version ("1.6.21") apply (false)
    id("org.jetbrains.kotlin.jvm") version ("1.6.21") apply (false)
}
group = properties["artifact.group"].toString()
version = properties["artifact.version"].toString()

tasks.register<Delete>("clean") {
    description = "Delete directory build"
    group = "build"
    delete(rootProject.buildDir)
}

tasks.register<GradleBuild>("serve") {
    description = "lance le server backend de l'application"
    dir = File("backend/build.gradle")
    tasks = listOf("bootRun")
}
