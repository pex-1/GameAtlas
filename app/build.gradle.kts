plugins {
    alias(libs.plugins.gameatlas.android.application.compose)
    alias(libs.plugins.gameatlas.jvm.ktor)
    alias(libs.plugins.gameatlas.android.junit5)
    alias(libs.plugins.gameatlas.android.application.firebase)
    alias(libs.plugins.gameatlas.android.application.jacoco)
}

android {
    namespace = "com.example.gameatlas"

    defaultConfig {
        testInstrumentationRunner = "com.example.core.android_test.KoinTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.navigation.compose)
    // Koin
    implementation(libs.bundles.koin)

    // Crypto
    implementation(libs.androidx.security.crypto.ktx)

    //Timber
    implementation(libs.timber)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    api(libs.core)

    // Local Tests
    testImplementation(libs.bundles.local.tests)

    // Instrumented Tests
    androidTestImplementation(libs.bundles.instrumented.tests)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // modules
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.database)
    implementation(projects.core.network)

    implementation(projects.feature.home)
    implementation(projects.feature.genres)
    implementation(projects.feature.details)

}