import Constants.BLANK

object DomainDeps {

    const val KOIN_VERSION = "koin_version"

    @JvmStatic
    val domainDeps by lazy {
        mapOf("io.insert-koin:koin-core" to KOIN_VERSION)
    }

    @JvmStatic
    val domainTestDeps by lazy {
        mapOf(
            "org.jetbrains.kotlin:kotlin-test" to BLANK,
            "org.jetbrains.kotlin:kotlin-test-junit" to BLANK,
            "io.insert-koin:koin-test" to KOIN_VERSION,
            "io.insert-koin:koin-test-junit4" to KOIN_VERSION,
        )
    }
}
