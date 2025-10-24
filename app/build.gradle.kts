import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "team.kavach.kanak"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "team.kavach.kanak"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val localProperties = Properties();
    val localPropertiesFile = File(rootDir, "secret.properties")

    if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "WEATHER_API_KEY", "${localProperties["WEATHER_API_KEY"]}")
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            buildConfigField("String", "WEATHER_API_KEY", "${localProperties["WEATHER_API_KEY"]}")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("17")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.play.services.tflite.support)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.coil.compose)


    // ViewModel
    implementation(libs.lifecycle.viewmodel.compose)

    // Retrofit & gson parser
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.convertor)


    // Extension Icon Pack of material
    implementation(libs.androidx.compose.material.icons.extended)

    // Shapes
    implementation(libs.graphics.shapes)

    // Nav compose
    implementation(libs.androidx.navigation.compose)


    // Room DB
    // implementation(libs.androidx.room.compiler)
    // Annotation for Room
}