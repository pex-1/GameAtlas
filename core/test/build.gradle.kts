plugins {
    alias(libs.plugins.gameatlas.jvm.library)
    alias(libs.plugins.gameatlas.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.junit5.api)
    implementation(libs.coroutines.test)
}