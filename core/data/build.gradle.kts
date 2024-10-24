plugins {
    alias(libs.plugins.gameatlas.android.library)
    alias(libs.plugins.gameatlas.jvm.ktor)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(libs.androidx.paging.common)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}