import BuildDeps.KOTLIN_VERSION
import Constants.BLANK
import DomainDeps.KOIN_VERSION

object AppDeps {
    private const val androidTestImplementation = "androidTestImplementation"
    private const val implementation = "implementation"
    private const val testImplementation = "testImplementation"
    private const val kapt = "kapt"
    private const val annotationProcessor = "annotationProcessor"
    private const val testAnnotationProcessor = "testAnnotationProcessor"

    private const val ROOM_VERSION = "room_version"
    private const val ANDROIDX_CORE_VERSION = "androidx_core_version"
    private const val MOCKITO_KOTLIN_VERSION = "mockito_kotlin_version"
    internal const val NAV_VERSION = "nav_version"
    private const val KOIN_ANDROID_VERSION = "koin_android_version"
    private const val RETROFIT_VERSION = "retrofit_version"
    private const val MATERIAL_VERSION = "material_version"
    private const val ESPRESSO_VERSION = "espresso_version"
    private const val ANDROIDX_LIFECYCLE_VERSION = "androidx_lifecycle_version"
    private const val ANDROIDX_JUNIT_VERSION = "androidx_junit_version"
    private const val CONSTRAINT_LAYOUT_VERSION = "constraint_layout_version"
    private const val ANDROIDX_ARCH_CORE_VERSION = "androidx_arch_core_version"
    private const val APP_COMPAT_VERSION = "app_compat_version"
    private const val KOTLINX_COROUTINES_VERSION = "kotlinx_coroutines_version"



    @JvmStatic
    val appModules by lazy {
        mapOf(
            implementation to androidDeps,
            testImplementation to testDeps,
            androidTestImplementation to androidTestDeps,
            kapt to kaptDeps,
            annotationProcessor to annotationProcessorDeps,
            testAnnotationProcessor to testAnnotationProcessorDeps,
        )
    }

    @JvmStatic
    val kaptDeps by lazy {
        mapOf("androidx.room:room-compiler" to ROOM_VERSION)
    }

    @JvmStatic
    val annotationProcessorDeps by lazy { emptyMap<String, String>() }

    @JvmStatic
    val testAnnotationProcessorDeps by lazy {
        mapOf("androidx.room:room-compiler" to ROOM_VERSION)
    }

    @JvmStatic
    val androidDeps by lazy {
        mapOf(
            "androidx.core:core-ktx" to ANDROIDX_CORE_VERSION,
            "androidx.appcompat:appcompat" to APP_COMPAT_VERSION,
            "com.google.android.material:material" to MATERIAL_VERSION,
            "androidx.constraintlayout:constraintlayout" to CONSTRAINT_LAYOUT_VERSION,
            "org.jetbrains.kotlinx:kotlinx-coroutines-core" to KOTLINX_COROUTINES_VERSION,
            "org.jetbrains.kotlinx:kotlinx-coroutines-android" to KOTLINX_COROUTINES_VERSION,
            "androidx.navigation:navigation-fragment-ktx" to NAV_VERSION,
            "androidx.navigation:navigation-ui-ktx" to NAV_VERSION,
            "androidx.navigation:navigation-dynamic-features-fragment" to NAV_VERSION,
            "androidx.room:room-runtime" to ROOM_VERSION,
            "androidx.room:room-guava" to ROOM_VERSION,
            "androidx.room:room-paging" to ROOM_VERSION,
            "androidx.lifecycle:lifecycle-viewmodel-ktx" to ANDROIDX_LIFECYCLE_VERSION,
            "androidx.lifecycle:lifecycle-livedata-ktx" to ANDROIDX_LIFECYCLE_VERSION,
            "androidx.lifecycle:lifecycle-common-java8" to ANDROIDX_LIFECYCLE_VERSION,
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7" to KOTLIN_VERSION,
            "androidx.constraintlayout:constraintlayout" to CONSTRAINT_LAYOUT_VERSION,
            "com.google.android.material:material" to MATERIAL_VERSION,
            "io.insert-koin:koin-core" to KOIN_VERSION,
            "io.insert-koin:koin-android" to KOIN_ANDROID_VERSION,
            "io.insert-koin:koin-androidx-workmanager" to KOIN_ANDROID_VERSION,
            "io.insert-koin:koin-androidx-navigation" to KOIN_ANDROID_VERSION,
        )
    }

    @JvmStatic
    val androidTestDeps by lazy {
        mapOf(
            "org.jetbrains.kotlin:kotlin-test" to BLANK,
            "org.jetbrains.kotlin:kotlin-test" to BLANK,
            "org.jetbrains.kotlin:kotlin-test-junit" to BLANK,
            "androidx.test.ext:junit" to ANDROIDX_JUNIT_VERSION,
            "org.mockito.kotlin:mockito-kotlin" to MOCKITO_KOTLIN_VERSION,
            "androidx.navigation:navigation-testing" to NAV_VERSION,
            "androidx.arch.core:core-testing" to ANDROIDX_ARCH_CORE_VERSION,
            "androidx.test.espresso:espresso-core" to ESPRESSO_VERSION,
            "androidx.test.ext:junit" to ANDROIDX_JUNIT_VERSION,
            "io.insert-koin:koin-test" to KOIN_VERSION,
            "io.insert-koin:koin-test-junit4" to KOIN_VERSION,
        )
    }

    @JvmStatic
    val testDeps by lazy {
        mapOf(
            "org.mockito.kotlin:mockito-kotlin" to MOCKITO_KOTLIN_VERSION,
            "org.mockito.kotlin:mockito-kotlin" to MOCKITO_KOTLIN_VERSION,
            "com.squareup.retrofit2:retrofit" to RETROFIT_VERSION,
            "com.squareup.retrofit2:converter-moshi" to RETROFIT_VERSION,
            "androidx.room:room-testing" to ROOM_VERSION,
            "io.insert-koin:koin-test" to KOIN_VERSION,
            "io.insert-koin:koin-test-junit4" to KOIN_VERSION,
        )
    }
}