plugins {
    alias(libs.plugins.gameatlas.android.feature.ui)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.feature.genres"
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.timber)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.compose.animation)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}