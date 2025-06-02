plugins {
    alias(libs.plugins.movieJunkie.android.library)
    alias(libs.plugins.movieJunkie.android.hilt)
    alias(libs.plugins.movieJunkie.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.feature.moviedetail"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.foundation.android)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}