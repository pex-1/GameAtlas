plugins {
    alias(libs.plugins.gameatlas.android.library)
    alias(libs.plugins.gameatlas.jvm.ktor)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.data)
}