import AppDeps.appModules
import BuildDeps.buildDependencies
import BuildTools.dependency
import BuildTools.jdlFile
import BuildTools.webAppSrc
import Constants.WEBAPP
import Constants.WEBAPP_SRC
import DomainDeps.domainDeps
import DomainDeps.domainTestDeps
import java.io.ByteArrayOutputStream
import java.lang.System.getProperty


/*=================================================================================*/
buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        @Suppress("RemoveRedundantQualifierName")
        BuildDeps.buildDependencies
            .forEach { classpath("${it.key}:${properties[it.value]}") }
    }
}
/*=================================================================================*/
plugins {
    kotlin("jvm") version Versions.kotlin_version apply false
    kotlin("android") version Versions.kotlin_version apply false
    id("com.android.application") version Versions.android_app_version apply false
    id("com.android.library") version Versions.android_lib_version apply false
}
/*=================================================================================*/
tasks.register<Delete>("clean") {
    description = "Delete directory build"
    group = "build"
    doLast { delete(rootProject.buildDir) }
}
/*=================================================================================*/
