import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.JavaVersion.VERSION_1_8
import BuildTools.dependency
import DomainDeps.domainDeps
import DomainDeps.domainTestDeps

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("java-library")
}

dependencies {
    domainDeps.forEach { implementation(dependency(it)) }
    domainTestDeps.forEach { testImplementation(dependency(it)) }
//    setOf("io.arrow-kt:arrow-core:1.2.0-RC",
//        "io.arrow-kt:arrow-fx-coroutines:1.2.0-RC").map{
//        implementation(it){
//            exclude(group="kotlin-stdlib")
//        }
//    }
}

java {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_11
}