plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)

    /**
     * Compose
     */
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)

    /**
     * Dependency Injection with Hilt
     */
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.uicomponentstest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.uicomponentstest"
        minSdk = 33
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    /**
     * Code Scanner
     */
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")

    /**
     * Data Store
     */
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    /**
     * Compose
     */
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.0-rc01")
    implementation("androidx.navigation:navigation-compose:2.8.0-rc01")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    /**
     * Dependency Injection with Hilt
     */
    ksp("com.google.dagger:hilt-android-compiler:2.52")
    implementation("com.google.dagger:hilt-android:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    /**
     * Material Design, MDC
     */
    implementation("com.google.android.material:material:1.12.0")
}