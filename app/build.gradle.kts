plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
}

android {
    namespace = "barant.curso.simpsonsapi"
    compileSdk = 36

    defaultConfig {
        applicationId = "barant.curso.simpsonsapi"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Unit test
    testImplementation(libs.junit)
    // MockK
    testImplementation(libs.mockk)
    // Corrutinas (si usas coroutines)
    testImplementation(libs.kotlinx.coroutines.test)
    // Test viewModel
    testImplementation(libs.androidx.core.testing)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test.v160)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    //Img
    implementation(libs.coil3.coil)
    implementation(libs.coil.network.okhttp)

// Retrofit
    implementation(libs.retrofit.v300)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json.v190)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
// Converter para Kotlinx Serialization
    implementation(libs.converter.kotlinx.serialization)
// OkHttp
    implementation(libs.okhttp)
// Logging interceptor para debug
    implementation(libs.okhttp3.logging.interceptor)
// Kotlinx Serialization JSON
    implementation(libs.kotlinx.serialization.json.v190)
// Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Native
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(kotlin("test"))
}