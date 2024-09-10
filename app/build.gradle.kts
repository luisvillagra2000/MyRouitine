plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.paparazzi)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.myroutine"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myroutine"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_AUTH_TOKEN", findProperty("API_AUTH_TOKEN") as String)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core library dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Dependency Injection
    implementation(libs.koin.android)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    // Unit Test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    implementation(libs.androidx.junit.ktx)

    // Screenshot Test dependencies
    testImplementation(libs.paparazzi)

    // UI Test dependencies
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
