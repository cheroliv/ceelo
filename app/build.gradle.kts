@file:Suppress("UnstableApiUsage")

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
dependencies {
//    implementation(platform("io.arrow-kt:arrow-stack:1.2.0-RC"))
//    implementation("io.arrow-kt:arrow-core")
//    implementation("io.arrow-kt:arrow-fx-coroutines")

    implementation("io.insert-koin:koin-core:3.2.2")
    //TODO: https://blog.devgenius.io/out-with-retrofit-and-in-with-ktor-client-e8b52f205139
    implementation("io.ktor:ktor-client-core:2.2.4")
    implementation("io.ktor:ktor-client-cio:2.2.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.2.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
//            "com.squareup.retrofit2:retrofit" to 2.9.0,
//            "com.squareup.retrofit2:converter-moshi" to 2.9.0,

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("io.insert-koin:koin-test:3.2.2")
    testImplementation("io.insert-koin:koin-test-junit4:3.2.2")
    testImplementation("io.ktor:ktor-client-mock:2.2.4")
    testImplementation("ch.qos.logback:logback-classic:1.4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("androidx.room:room-testing:2.4.3")

    testAnnotationProcessor("androidx.room:room-compiler:2.4.3")

    kapt("androidx.room:room-compiler:2.4.3")


    androidTestImplementation("org.jetbrains.kotlin:kotlin-test")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0") {
        exclude("com.android.support", "support-annotations")
    }
    androidTestImplementation("io.insert-koin:koin-test:3.3.0")
    androidTestImplementation("io.insert-koin:koin-test-junit4:3.2.2")
    androidTestImplementation("androidx.room:room-testing:2.4.3")
    androidTestImplementation("io.ktor:ktor-client-mock:2.2.4")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.5.3")
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-guava:2.4.3")
    implementation("androidx.room:room-paging:2.4.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.20")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.7.0")
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-android:3.3.0")
    implementation("io.insert-koin:koin-androidx-workmanager:3.3.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.3.0")
}
/*=================================================================================*/
