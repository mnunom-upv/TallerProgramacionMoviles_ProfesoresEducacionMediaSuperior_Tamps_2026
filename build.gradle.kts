plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a2026_01_holamundoandroid"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.a2026_01_holamundoandroid"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.documentfile)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Requerdia para DEMO 13
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    // Requerida para Demo 15:
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    // En la seccion "dependencyResolutionManagement {" del archivo settings.graddle.kts, agregar la siguiente linea:
    //maven { url = uri("https://jitpack.io") }
}