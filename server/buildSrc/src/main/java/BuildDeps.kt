@file:Suppress("MemberVisibilityCanBePrivate")

import AppDeps.NAV_VERSION

object BuildDeps {
    const val JACKSON_VERSION = "jackson_version"
    const val PUBLISHER_VERSION = "publisher_version"
    const val KOTLIN_VERSION = "kotlin_version"

    @JvmStatic
    val buildDependencies: Map<String, String> by lazy {
        mapOf(
            "org.jetbrains.kotlin:kotlin-gradle-plugin" to KOTLIN_VERSION,
            "androidx.navigation:navigation-safe-args-gradle-plugin" to NAV_VERSION,
            "com.fasterxml.jackson.module:jackson-module-kotlin" to JACKSON_VERSION,
            "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml" to JACKSON_VERSION,
            "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to JACKSON_VERSION,
            "com.github.triplet.gradle:play-publisher" to PUBLISHER_VERSION,
        )
    }
}