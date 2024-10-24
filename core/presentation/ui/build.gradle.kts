plugins {
    alias(libs.plugins.gameatlas.android.library.compose)
    alias(libs.plugins.gameatlas.android.library.jacoco)
}

android {
    namespace = "com.example.core.presentation.ui"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}