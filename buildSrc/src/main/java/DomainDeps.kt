@file:Suppress("MemberVisibilityCanBePrivate")

import AppDeps.MOCKITO_KOTLIN_VERSION
import Constants.BLANK

object DomainDeps {
    const val KOIN_VERSION = "koin_version"
    const val KTOR_VERSION = "ktor_version"
    const val LOGBACK_VERSION = "logback_version"
    const val ARROW_KT_VERSION = "arrow-kt_version"


    @JvmStatic
    val domainDeps by lazy {
        mapOf(
            "io.insert-koin:koin-core" to KOIN_VERSION,
            "io.ktor:ktor-client-core" to KTOR_VERSION,
            "io.ktor:ktor-client-cio" to KTOR_VERSION,
            "io.ktor:ktor-client-content-negotiation" to KTOR_VERSION,
            "io.ktor:ktor-serialization-kotlinx-json" to KTOR_VERSION,

        )
    }

    @JvmStatic
    val domainTestDeps by lazy {
        mapOf(
            "org.jetbrains.kotlin:kotlin-test" to BLANK,
            "org.jetbrains.kotlin:kotlin-test-junit" to BLANK,
            "org.mockito.kotlin:mockito-kotlin" to MOCKITO_KOTLIN_VERSION,
            "io.insert-koin:koin-test" to KOIN_VERSION,
            "io.insert-koin:koin-test-junit4" to KOIN_VERSION,
            "io.ktor:ktor-client-mock" to KTOR_VERSION,
            "ch.qos.logback:logback-classic" to LOGBACK_VERSION,
        )
    }
}