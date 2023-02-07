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
project.tasks.register<Exec>("gradleStop") {
    group = WEBAPP
    description = "Stop any gradle daemons running!" + "\n" +
            "use system gradle to launch gradle --stop task, to kill webapp process"
    workingDir = rootDir
    standardOutput = ByteArrayOutputStream()
    @Suppress("LeakingThis")
    commandLine(buildString {
        append(getProperty("user.home"))
        append("/.sdkman/candidates/gradle/current/bin/gradle")
    }, "--stop")
    doLast { logger.info(standardOutput.toString()) }
}
/*=================================================================================*/
tasks.register<GradleBuild>("serve") {
    group = WEBAPP
    description = "launch ceelo backend web application"
    dir = jdlFile.parentFile
    tasks = listOf(":bootRun")
}
/*=================================================================================*/
tasks.register<GradleBuild>("checkWebapp") {
    group = WEBAPP
    description = "launch ceelo backend web application"
    doFirst { dir = jdlFile.parentFile }
    tasks = listOf(":check")
}
/*=================================================================================*/
tasks.register<Exec>("jdl") {
//delete webapp
//create webapp
//copier ceelo.jdl
//copier yo-rc.json?
    group = WEBAPP
    description = "launch jdl source generator"
    standardOutput = ByteArrayOutputStream()
    doFirst {
        jdlFile.run jdl@{
            workingDir = this@jdl.parentFile
            commandLine(
                "jhipster",
                "jdl",
                this@jdl.name,
                "--force"
            )
        }
    }
//   doFirst { dependsOn("exportWebappSource","nvmAdjust")
//   webAppSrc.forEach { copysrc(it, WEBAPP, WEBAPP_SRC) }}
//   doLast { finalizedBy("syncWebappSource")
//   webAppSrc.forEach { copysrc(it, WEBAPP_SRC, WEBAPP) }}
}

/*=================================================================================*/
