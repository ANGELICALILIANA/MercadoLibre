plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.mercadolibre"
    compileSdk = 35

    defaultConfig {
        val baseUrl: String by project
        applicationId = "com.example.mercadolibre"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
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
        compose = true
        buildConfig = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.livedata)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.kotlin)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.compose.coil)
    implementation(libs.androidx.navigation.testing.android)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit.test)
    testImplementation(libs.androidx.mockito.core)
    testImplementation(libs.androidx.mockito.kotlin)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.dagger.hilt.android.compiler)
    kapt(libs.androidx.room.compiler)
    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
}