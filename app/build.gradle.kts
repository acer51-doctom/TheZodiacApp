plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.compose") // DO NOT specify version here
}

android {
    namespace = "com.acer51.TheZodiacApp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.acer51.TheZodiacApp"

        // This line sets the minimum supported Android version.
        // API 26 corresponds to Android 8.0 (Oreo).
        // This configuration already supports Android 8.0 and newer.
        minSdk = 26

        targetSdk = 34
        versionCode = 1
        versionName = "0.4.0"

        vectorDrawables.useSupportLibrary = true

        // This file is already configured for a single, Universal APK (all architectures)
        // because you have NOT included any 'splits' or 'ndk { abiFilters }' blocks.
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // ⚠️ The following block was removed because it uses a deprecated and brittle method
    // for renaming APKs. For CI/CD (like GitHub Actions), it's best to let Gradle use
    // its default naming convention (e.g., app-release-unsigned.apk) and handle the
    // renaming when you upload the artifact in the workflow.
    /*
    applicationVariants.all {
        outputs.all {
            val buildTypeName = name // debug or release
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                "TheZodiacApp-${buildTypeName}.apk"
        }
    }
    */
}

dependencies {
    // Core and Compose dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.5.4") // Adjusted to 1.5.4 for consistency
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4") // Adjusted to 1.5.4 for consistency
    implementation("com.google.android.material:material:1.12.0")

    // Testing and Debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4") // Adjusted to 1.5.4 for consistency
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
}