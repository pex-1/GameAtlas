plugins {
    alias(libs.plugins.gameatlas.android.library)
    alias(libs.plugins.gameatlas.android.junit5)
}

android {
    namespace = "com.example.core.android_test"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.network)
    api(projects.core.test)

    implementation(libs.androidx.runner)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)
    implementation(libs.coroutines.test)
}