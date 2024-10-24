plugins {
    alias(libs.plugins.gameatlas.android.library.compose)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.core.presentation.designsystem"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.compose.animation)

    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material3)
}