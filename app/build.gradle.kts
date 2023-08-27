//import Build_gradle.BuildDeps.KOTLIN_VERSION
import Build_gradle.AppConfig.androidTestInstrumentation
import Build_gradle.AppConfig.appId
import Build_gradle.AppConfig.currentCompileSdk
import Build_gradle.AppConfig.currentVersionCode
import Build_gradle.AppConfig.currentVersionName
import Build_gradle.AppConfig.minSdkVersion
import Build_gradle.AppConfig.proguardFile
import Build_gradle.AppConfig.proguardRules
import Build_gradle.AppConfig.targetSdkVersion
import Build_gradle.AppDeps.MOCKITO_KOTLIN_VERSION
import Build_gradle.AppDeps.ROOM_VERSION
import Build_gradle.AppDeps.androidDeps
import Build_gradle.AppDeps.androidTestDeps
import Build_gradle.AppDeps.androidTestImplementation
import Build_gradle.AppDeps.annotationProcessor
import Build_gradle.AppDeps.annotationProcessorDeps
import Build_gradle.AppDeps.implementation
import Build_gradle.AppDeps.kapt
import Build_gradle.AppDeps.kaptDeps
import Build_gradle.AppDeps.testAnnotationProcessor
import Build_gradle.AppDeps.testAnnotationProcessorDeps
import Build_gradle.AppDeps.testDeps
import Build_gradle.AppDeps.testImplementation
import Build_gradle.Constants.BLANK
import org.gradle.api.JavaVersion.VERSION_1_8

/*=================================================================================*/
plugins {
    kotlin("android")
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("com.github.triplet.play")
}
/*=================================================================================*/
dependencies {
    implementation("io.insert-koin:koin-core:${properties[DomainDeps.KOIN_VERSION]}")
    implementation("io.ktor:ktor-client-core:${properties[DomainDeps.KTOR_VERSION]}")
    implementation("io.ktor:ktor-client-cio:${properties[DomainDeps.KTOR_VERSION]}")
    implementation("io.ktor:ktor-client-content-negotiation:${properties[DomainDeps.KTOR_VERSION]}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${properties[DomainDeps.KTOR_VERSION]}")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${properties[MOCKITO_KOTLIN_VERSION]}")
    testImplementation("io.insert-koin:koin-test:${properties[DomainDeps.KOIN_VERSION]}")
    testImplementation("io.insert-koin:koin-test-junit4:${properties[DomainDeps.KOIN_VERSION]}")
    testImplementation("io.ktor:ktor-client-mock:${properties[DomainDeps.KTOR_VERSION]}")
    testImplementation("ch.qos.logback:logback-classic:${properties[DomainDeps.LOGBACK_VERSION]}")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("androidx.room:room-testing:${properties[ROOM_VERSION]}")

    testAnnotationProcessor("androidx.room:room-compiler:${properties[ROOM_VERSION]}")

    kapt("androidx.room:room-compiler:${properties[ROOM_VERSION]}")

    AppDeps.appModules.forEach { module ->
        module.value.forEach {
            when (it.key) {
                "androidx.test.espresso:espresso-core" ->
                    dependencies.add(module.key, it.run {
                        key + when (value) {
                            null -> BLANK
                            BLANK -> BLANK
                            else -> ":${properties[value]}"
                        }
                    }) {
                        exclude(
                            "com.android.support",
                            "support-annotations"
                        )
                    }

                else -> dependencies.add(module.key, it.run {
                    key + when (value) {
                        null -> BLANK
                        BLANK -> BLANK
                        else -> ":${properties[value]}"
                    }
                })
            }
        }
    }
//    implementation(platform("io.arrow-kt:arrow-stack:1.2.0-RC"))
//    implementation("io.arrow-kt:arrow-core")
//    implementation("io.arrow-kt:arrow-fx-coroutines")
}
/*=================================================================================*/
android {
    namespace = appId
    compileSdk = currentCompileSdk
    defaultConfig {
        applicationId = appId
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
        versionCode = currentVersionCode
        versionName = currentVersionName
        testInstrumentationRunner = androidTestInstrumentation
        javaCompileOptions {
            mapOf(
                "room.schemaLocation" to "$projectDir/schemas",
                "room.incremental" to "true",
                "room.expandProjection" to "true"
            ).forEach { annotationProcessorOptions.arguments[it.key] = it.value }
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(proguardFile), proguardRules)
        }
    }
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    kotlinOptions { jvmTarget = VERSION_1_8.toString() }
    viewBinding { android.buildFeatures.viewBinding = true }
    packagingOptions { resources.excludes.add("META-INF/atomicfu.kotlin_module") }
}
/*=================================================================================*/

object Versions {
    const val android_app_version = "7.2.1"
    const val android_lib_version = "7.3.1"
    const val kotlin_version = "1.7.20"
}

/*=================================================================================*/


/*=================================================================================*/
object Constants {

    const val BLANK = ""
    const val DELIM = ","
}
/*=================================================================================*/

object AppConfig {
    const val currentCompileSdk = 33
    const val minSdkVersion = 26
    const val targetSdkVersion = 33
    const val currentVersionCode = 11
    const val currentVersionName = "0.0.11"
    const val appId = "game.ceelo"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardFile = "proguard-android-optimize.txt"
    const val proguardRules = "proguard-rules.pro"
}
/*=================================================================================*/

object DomainDeps {
    const val KOIN_VERSION = "koin_version"
    const val KTOR_VERSION = "ktor_version"
    const val LOGBACK_VERSION = "logback_version"
    const val ARROW_KT_VERSION = "arrow-kt_version"

}
/*=================================================================================*/

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
            "androidx.lifecycle:lifecycle-runtime-ktx" to ANDROIDX_LIFECYCLE_VERSION,
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
}


/*=================================================================================*/
