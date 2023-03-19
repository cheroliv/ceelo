@file:Suppress("MemberVisibilityCanBePrivate")

import BuildDeps.KOTLIN_VERSION
import Constants.BLANK
import DomainDeps.KOIN_VERSION
import DomainDeps.KTOR_VERSION

object AppDeps {
     const val androidTestImplementation = "androidTestImplementation"
     const val implementation = "implementation"
     const val testImplementation = "testImplementation"
     const val kapt = "kapt"
     const val annotationProcessor = "annotationProcessor"
     const val testAnnotationProcessor = "testAnnotationProcessor"

     const val ROOM_VERSION = "room_version"
     const val ANDROIDX_CORE_VERSION = "androidx_core_version"
     const val MOCKITO_KOTLIN_VERSION = "mockito_kotlin_version"
     const val NAV_VERSION = "nav_version"
     const val KOIN_ANDROID_VERSION = "koin_android_version"
     const val RETROFIT_VERSION = "retrofit_version"
     const val MATERIAL_VERSION = "material_version"
     const val ESPRESSO_VERSION = "espresso_version"
     const val ANDROIDX_LIFECYCLE_VERSION = "androidx_lifecycle_version"
     const val ANDROIDX_JUNIT_VERSION = "androidx_junit_version"
     const val CONSTRAINT_LAYOUT_VERSION = "constraint_layout_version"
     const val ANDROIDX_ARCH_CORE_VERSION = "androidx_arch_core_version"
     const val APP_COMPAT_VERSION = "app_compat_version"
     const val KOTLINX_COROUTINES_VERSION = "kotlinx_coroutines_version"


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
//            "com.squareup.retrofit2:retrofit" to RETROFIT_VERSION,
//            "com.squareup.retrofit2:converter-moshi" to RETROFIT_VERSION,
       //TODO: https://blog.devgenius.io/out-with-retrofit-and-in-with-ktor-client-e8b52f205139
            "io.ktor:ktor-client-core" to KTOR_VERSION,
            "io.ktor:ktor-client-cio" to KTOR_VERSION,
        )
    }

    @JvmStatic
    val androidTestDeps by lazy {
        mapOf(
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
            "androidx.room:room-testing" to ROOM_VERSION,
            "io.ktor:ktor-client-mock" to KTOR_VERSION,
        )
    }

    @JvmStatic
    val testDeps by lazy {
        mapOf(
            "org.jetbrains.kotlin:kotlin-test" to BLANK,
            "org.jetbrains.kotlin:kotlin-test-junit" to BLANK,
            "org.mockito.kotlin:mockito-kotlin" to MOCKITO_KOTLIN_VERSION,
            "androidx.room:room-testing" to ROOM_VERSION,
            "io.insert-koin:koin-test" to KOIN_VERSION,
            "io.insert-koin:koin-test-junit4" to KOIN_VERSION,
            "io.ktor:ktor-client-mock" to KTOR_VERSION,
        )
    }
}