plugins {
    `kotlin-dsl`
}

group = "com.example.gameatlas.buildLogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "gameatlas.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "gameatlas.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "gameatlas.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "gameatlas.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "gameatlas.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "gameatlas.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "gameatlas.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "gameatlas.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
        register("androidFirebase") {
            id = "gameatlas.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("jvmJunit5") {
            id = "gameatlas.jvm.junit5"
            implementationClass = "JvmJUnit5ConventionPlugin"
        }
        register("androidJunit5") {
            id = "gameatlas.android.junit5"
            implementationClass = "AndroidJUnit5ConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "gameatlas.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "gameatlas.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
    }
}