@file:Suppress("UnstableApiUsage")

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
import Build_gradle.AppDeps.KTOR_VERSION
import Build_gradle.AppDeps.LOGBACK_VERSION
import Build_gradle.AppDeps.MATERIAL_VERSION
import Build_gradle.AppDeps.MOCKITO_KOTLIN_VERSION
import Build_gradle.AppDeps.NAV_VERSION
import Build_gradle.AppDeps.ROOM_VERSION
import org.gradle.api.JavaVersion.VERSION_1_8

/*=================================================================================*/
plugins {
    kotlin("android")
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("com.github.triplet.play")
    kotlin("plugin.serialization")
}
/*=================================================================================*/

/*=================================================================================*/
android {
    namespace = "game.ceelo"
    compileSdk = 33
    defaultConfig {
        applicationId = "game.ceelo"
        minSdk = 26
        targetSdk = 33
        versionCode = 11
        versionName = "0.0.11"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ), "proguard-rules.pro"
            )
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
object AppDeps {
//    retrofit_version=2.9.0
//    kotlinx_coroutines_version=1.6.1
//    nav_version=2.5.3
//    kotlin_version=1.7.20
//    activityVersion=1.4.0
//    app_compat_version=1.5.0
//    constraint_layout_version=2.1.4
//    androidx_arch_core_version=2.1.0
//    coroutines_version=1.3.9
//    lifecycle_version=2.4.1
//    material_version=1.7.0
//    room_version=2.4.3
//    espresso_version=3.4.0
//    androidx_junit_version=1.1.4
//    koin_android_version=3.3.0
//    mockito_kotlin_version=4.0.0
//    publisher_version=3.7.0
//    androidx_core_version=1.9.0
//    kotlinx_serialization_json.version=1.3.0
//    springmockk.version=3.1.0
//    mockk.version=1.12.0
//    wiremock.version=2.31.0
//    jackson_version=2.14.1


    const val KOTLIN_VERSION = "kotlin_version"
    const val ROOM_VERSION = "room_version"
    const val ANDROIDX_CORE_VERSION = "androidx_core_version"
    const val MOCKITO_KOTLIN_VERSION = "mockito_kotlin_version"
    const val NAV_VERSION = "nav_version"
    const val KOIN_ANDROID_VERSION = "koin_android_version"
    const val RETROFIT_VERSION = "retrofit_version"
    const val MATERIAL_VERSION = "material_version"
    const val ESPRESSO_VERSION = "espresso_version"
    const val ANDROIDX_LIFECYCLE_VERSION = "androidx_lifecycle_version"//2.5.1
    const val ANDROIDX_JUNIT_VERSION = "androidx_junit_version"
    const val CONSTRAINT_LAYOUT_VERSION = "constraint_layout_version"
    const val ANDROIDX_ARCH_CORE_VERSION = "androidx_arch_core_version"
    const val APP_COMPAT_VERSION = "app_compat_version"
    const val KOTLINX_COROUTINES_VERSION = "kotlinx_coroutines_version"


    const val KTOR_VERSION = "ktor_version"//2.2.4
    const val LOGBACK_VERSION = "logback_version"//1.3.6
    const val ARROW_KT_VERSION = "arrow-kt_version"
}
/*=================================================================================*/
fun String.dep(version:String) = "$this${properties[version]}"

/*=================================================================================*/
dependencies {
//    implementation(platform("io.arrow-kt:arrow-stack:1.2.0-RC"))
//    implementation("io.arrow-kt:arrow-core")
//    implementation("io.arrow-kt:arrow-fx-coroutines")

    implementation("io.insert-koin:koin-core:3.2.2")
    //TODO: https://blog.devgenius.io/out-with-retrofit-and-in-with-ktor-client-e8b52f205139
    implementation("io.ktor:ktor-client-core:".dep(KTOR_VERSION))
    implementation("io.ktor:ktor-client-cio:".dep(KTOR_VERSION))
    implementation("io.ktor:ktor-client-content-negotiation:".dep(KTOR_VERSION))
    implementation("io.ktor:ktor-serialization-kotlinx-json:".dep(KTOR_VERSION))
//            "com.squareup.retrofit2:retrofit" to RETROFIT_VERSION,
//            "com.squareup.retrofit2:converter-moshi" to RETROFIT_VERSION,

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.mockito.kotlin:mockito-kotlin:".dep(MOCKITO_KOTLIN_VERSION))
    testImplementation("io.insert-koin:koin-test:3.2.2")
    testImplementation("io.insert-koin:koin-test-junit4:3.2.2")
    testImplementation("io.ktor:ktor-client-mock:".dep(KTOR_VERSION))
    testImplementation("ch.qos.logback:logback-classic:".dep(LOGBACK_VERSION))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("androidx.room:room-testing:".dep(ROOM_VERSION))

    testAnnotationProcessor("androidx.room:room-compiler:".dep(ROOM_VERSION))

    kapt("androidx.room:room-compiler:".dep(ROOM_VERSION))


    androidTestImplementation("org.jetbrains.kotlin:kotlin-test")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    androidTestImplementation("androidx.test.ext:junit:".dep(ANDROIDX_JUNIT_VERSION))
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:".dep(MOCKITO_KOTLIN_VERSION))
    androidTestImplementation("androidx.navigation:navigation-testing:".dep(NAV_VERSION))
    androidTestImplementation("androidx.arch.core:core-testing:".dep(ANDROIDX_ARCH_CORE_VERSION))
    androidTestImplementation("androidx.test.espresso:espresso-core:".dep(ESPRESSO_VERSION)) {
        exclude("com.android.support", "support-annotations")
    }
    androidTestImplementation("io.insert-koin:koin-test:".dep(KOIN_ANDROID_VERSION))
    androidTestImplementation("io.insert-koin:koin-test-junit4:3.2.2")
    androidTestImplementation("androidx.room:room-testing:".dep(ROOM_VERSION))
    androidTestImplementation("io.ktor:ktor-client-mock:".dep(KTOR_VERSION))


    implementation("androidx.core:core-ktx:".dep(ANDROIDX_CORE_VERSION))
    implementation("androidx.appcompat:appcompat:".dep(APP_COMPAT_VERSION))
    implementation("com.google.android.material:material:".dep(MATERIAL_VERSION))
    implementation("androidx.constraintlayout:constraintlayout:".dep(CONSTRAINT_LAYOUT_VERSION))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:".dep(KOTLINX_COROUTINES_VERSION))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:".dep(KOTLINX_COROUTINES_VERSION))
    implementation("androidx.navigation:navigation-fragment-ktx:".dep(NAV_VERSION))
    implementation("androidx.navigation:navigation-ui-ktx:".dep(NAV_VERSION))
    implementation("androidx.navigation:navigation-dynamic-features-fragment:".dep(NAV_VERSION))
    implementation("androidx.room:room-runtime:".dep(ROOM_VERSION))
    implementation("androidx.room:room-guava:".dep(ROOM_VERSION))
    implementation("androidx.room:room-paging:".dep(ROOM_VERSION))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:".dep(ANDROIDX_LIFECYCLE_VERSION))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:".dep(ANDROIDX_LIFECYCLE_VERSION))
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:".dep(ANDROIDX_LIFECYCLE_VERSION))
    implementation("androidx.lifecycle:lifecycle-common-java8:".dep(ANDROIDX_LIFECYCLE_VERSION))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:".dep(KOTLIN_VERSION))
    implementation("androidx.constraintlayout:constraintlayout:".dep(CONSTRAINT_LAYOUT_VERSION))
    implementation("com.google.android.material:material:".dep(MATERIAL_VERSION))
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-android:".dep(KOIN_ANDROID_VERSION))
    implementation("io.insert-koin:koin-androidx-workmanager:".dep(KOIN_ANDROID_VERSION))
    implementation("io.insert-koin:koin-androidx-navigation:".dep(KOIN_ANDROID_VERSION))
}
/*=================================================================================*/
