plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.kotlin.unscramble"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.kotlin.unscramble"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    //ViewModel utilities for Compose
    val lifecycleVersion = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    //Add Ktor Engine
    val engineVersion = "2.3.7"
    implementation("io.ktor:ktor-client-android:$engineVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$engineVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$engineVersion")

    //Add coil library to get image from the internet
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Set up Room
    val roomVersion = "2.5.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    //Set up navigation in app
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    //Set up splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}