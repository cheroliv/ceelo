import Build_gradle.AppConfig.androidTestInstrumentation
import Build_gradle.AppConfig.appId
import Build_gradle.AppConfig.currentCompileSdk
import Build_gradle.AppConfig.currentVersionCode
import Build_gradle.AppConfig.currentVersionName
import Build_gradle.AppConfig.minSdkVersion
import Build_gradle.AppConfig.proguardFile
import Build_gradle.AppConfig.proguardRules
import Build_gradle.AppConfig.targetSdkVersion
import Build_gradle.AppDeps.ANDROIDX_ARCH_CORE_VERSION
import Build_gradle.AppDeps.ANDROIDX_CORE_VERSION
import Build_gradle.AppDeps.ANDROIDX_JUNIT_VERSION
import Build_gradle.AppDeps.ANDROIDX_LIFECYCLE_VERSION
import Build_gradle.AppDeps.APP_COMPAT_VERSION
import Build_gradle.AppDeps.CONSTRAINT_LAYOUT_VERSION
import Build_gradle.AppDeps.ESPRESSO_VERSION
import Build_gradle.AppDeps.KOIN_ANDROID_VERSION
import Build_gradle.AppDeps.KOTLINX_COROUTINES_VERSION
import Build_gradle.AppDeps.KOTLIN_VERSION
import Build_gradle.AppDeps.MATERIAL_VERSION
import Build_gradle.AppDeps.MOCKITO_KOTLIN_VERSION
import Build_gradle.AppDeps.NAV_VERSION
import Build_gradle.AppDeps.ROOM_VERSION
import Build_gradle.DomainDeps.KOIN_VERSION
import Build_gradle.DomainDeps.KTOR_VERSION
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
dependencies {
//    implementation(platform("io.arrow-kt:arrow-stack:1.2.0-RC"))
//    implementation("io.arrow-kt:arrow-core")
//    implementation("io.arrow-kt:arrow-fx-coroutines")

    implementation("io.insert-koin:koin-core:${properties[KOIN_VERSION]}")
    //TODO: https://blog.devgenius.io/out-with-retrofit-and-in-with-ktor-client-e8b52f205139
    implementation("io.ktor:ktor-client-core:${properties[KTOR_VERSION]}")
    implementation("io.ktor:ktor-client-cio:${properties[KTOR_VERSION]}")
    implementation("io.ktor:ktor-client-content-negotiation:${properties[KTOR_VERSION]}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${properties[KTOR_VERSION]}")
//            "com.squareup.retrofit2:retrofit" to RETROFIT_VERSION,
//            "com.squareup.retrofit2:converter-moshi" to RETROFIT_VERSION,

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${properties[MOCKITO_KOTLIN_VERSION]}")
    testImplementation("io.insert-koin:koin-test:${properties[KOIN_VERSION]}")
    testImplementation("io.insert-koin:koin-test-junit4:${properties[KOIN_VERSION]}")
    testImplementation("io.ktor:ktor-client-mock:${properties[KTOR_VERSION]}")
    testImplementation("ch.qos.logback:logback-classic:${properties[DomainDeps.LOGBACK_VERSION]}")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("androidx.room:room-testing:${properties[ROOM_VERSION]}")

    testAnnotationProcessor("androidx.room:room-compiler:${properties[ROOM_VERSION]}")

    kapt("androidx.room:room-compiler:${properties[ROOM_VERSION]}")


    androidTestImplementation("org.jetbrains.kotlin:kotlin-test")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    androidTestImplementation("androidx.test.ext:junit:${properties[ANDROIDX_JUNIT_VERSION]}")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:${properties[MOCKITO_KOTLIN_VERSION]}")
    androidTestImplementation("androidx.navigation:navigation-testing:${properties[NAV_VERSION]}")
    androidTestImplementation("androidx.arch.core:core-testing:${properties[ANDROIDX_ARCH_CORE_VERSION]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${properties[ESPRESSO_VERSION]}") {
        exclude("com.android.support", "support-annotations")
    }
    androidTestImplementation("io.insert-koin:koin-test:${properties[KOIN_ANDROID_VERSION]}")
    androidTestImplementation("io.insert-koin:koin-test-junit4:${properties[KOIN_VERSION]}")
    androidTestImplementation("androidx.room:room-testing:${properties[ROOM_VERSION]}")
    androidTestImplementation("io.ktor:ktor-client-mock:${properties[KTOR_VERSION]}")


    implementation("androidx.core:core-ktx:${properties[ANDROIDX_CORE_VERSION]}")
    implementation("androidx.appcompat:appcompat:${properties[APP_COMPAT_VERSION]}")
    implementation("com.google.android.material:material:${properties[MATERIAL_VERSION]}")
    implementation("androidx.constraintlayout:constraintlayout:${properties[CONSTRAINT_LAYOUT_VERSION]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${properties[KOTLINX_COROUTINES_VERSION]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${properties[KOTLINX_COROUTINES_VERSION]}")
    implementation("androidx.navigation:navigation-fragment-ktx:${properties[NAV_VERSION]}")
    implementation("androidx.navigation:navigation-ui-ktx:${properties[NAV_VERSION]}")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:${properties[NAV_VERSION]}")
    implementation("androidx.room:room-runtime:${properties[ROOM_VERSION]}")
    implementation("androidx.room:room-guava:${properties[ROOM_VERSION]}")
    implementation("androidx.room:room-paging:${properties[ROOM_VERSION]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${properties[ANDROIDX_LIFECYCLE_VERSION]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${properties[ANDROIDX_LIFECYCLE_VERSION]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${properties[ANDROIDX_LIFECYCLE_VERSION]}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${properties[ANDROIDX_LIFECYCLE_VERSION]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${properties[KOTLIN_VERSION]}")
    implementation("androidx.constraintlayout:constraintlayout:${properties[CONSTRAINT_LAYOUT_VERSION]}")
    implementation("com.google.android.material:material:${properties[MATERIAL_VERSION]}")
    implementation("io.insert-koin:koin-core:${properties[KOIN_VERSION]}")
    implementation("io.insert-koin:koin-android:${properties[KOIN_ANDROID_VERSION]}")
    implementation("io.insert-koin:koin-androidx-workmanager:${properties[KOIN_ANDROID_VERSION]}")
    implementation("io.insert-koin:koin-androidx-navigation:${properties[KOIN_ANDROID_VERSION]}")

}
/*=================================================================================*/
object Versions {
    const val android_app_version = "7.2.1"
    const val android_lib_version = "7.3.1"
    const val kotlin_version = "1.7.20"
}
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
    const val KOTLIN_VERSION = "kotlin_version"
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
}
/*=================================================================================*/
