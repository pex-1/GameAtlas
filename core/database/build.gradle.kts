plugins {
    alias(libs.plugins.gameatlas.android.library)
    alias(libs.plugins.gameatlas.android.room)
    alias(libs.plugins.gameatlas.android.junit5)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.core.database"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.koin)
    implementation(libs.timber)

    implementation(projects.core.domain)
}