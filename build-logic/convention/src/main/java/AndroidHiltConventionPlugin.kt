import com.build.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                "implementation"(libs.findLibrary("dagger-hilt-android").get())
                "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())
                "ksp"(libs.findLibrary("dagger-hilt-android-compiler").get())
            }
        }
    }
}