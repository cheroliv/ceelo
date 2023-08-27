import AppDeps.appModules
import Constants.BLANK
import Constants.DELIM

import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.exclude
import java.io.File
import java.lang.System.getProperty
import java.util.*
import kotlin.text.Charsets.UTF_8

/*=================================================================================*/
object BuildTools {
    @JvmStatic
    val sep: String by lazy { getProperty("file.separator") }

    /*=================================================================================*/
    @JvmStatic
    fun Project.dependency(entry: Map.Entry<String, String?>) = entry.run {
        key + when (value) {
            null -> BLANK
            BLANK -> BLANK
            else -> ":${properties[value]}"
        }
    }

    /*=================================================================================*/
    @JvmStatic
    fun Project.androidDependencies() {
        appModules.forEach { module ->
            module.value.forEach {
                when (it.key) {
                    "androidx.test.espresso:espresso-core" ->
                        dependencies.add(module.key, dependency(it)) {
                            exclude(
                                "com.android.support",
                                "support-annotations"
                            )
                        }

                    else -> dependencies.add(module.key, dependency(it))
                }
            }
        }
    }

    /*=================================================================================*/
}
