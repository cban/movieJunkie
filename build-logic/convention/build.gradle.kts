import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = " com.build.convention."
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
}
    gradlePlugin {
        plugins {
            register("androidApplication") {
                id = "movieJunkie.android.application"
                implementationClass = "AndroidApplicationConventionPlugin"
            }

            register("androidLibrary") {
                id = "movieJunkie.android.library"
                implementationClass = "AndroidLibraryConventionPlugin"
            }
            register("androidHilt") {
                id = "movieJunkie.android.hilt"
                implementationClass = "AndroidHiltConventionPlugin"
            }
        register("androidLibraryCompose") {
            id = "movieJunkie.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }

}