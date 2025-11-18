plugins {
    // MODIFIED: Use aliases from the version catalog
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.acer51.TheZodiacApp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.acer51.TheZodiacApp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.4.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // The commented-out 'applicationVariants' block can remain as is
}

dependencies {
    // MODIFIED: All dependencies now reference the version catalog

    // Core and Compose dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.google.material)
    implementation(libs.androidx.navigation.compose)

    // Testing and Debug dependencies
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}