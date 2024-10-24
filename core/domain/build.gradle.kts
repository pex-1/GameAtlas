plugins {
    alias(libs.plugins.gameatlas.jvm.library)
    alias(libs.plugins.gameatlas.jvm.junit5)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    //available on every single kotlin platform
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
    implementation(libs.kotlinx.serialization.core)

}