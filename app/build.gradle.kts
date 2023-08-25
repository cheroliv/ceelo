import AppConfig.androidTestInstrumentation
import AppConfig.appId
import AppConfig.currentCompileSdk
import AppConfig.currentVersionCode
import AppConfig.currentVersionName
import AppConfig.minSdkVersion
import AppConfig.proguardFile
import AppConfig.proguardRules
import AppConfig.targetSdkVersion
import org.gradle.api.JavaVersion.VERSION_1_8
import BuildTools.androidDependencies

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
    implementation(project(":domain"))
    androidDependencies()
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