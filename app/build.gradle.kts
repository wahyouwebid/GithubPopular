import config.AndroidConfig
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        namespace = AndroidConfig.APP_NAME_SPACE
        minSdk = AndroidConfig.MIN_SDK
        versionCode = AndroidConfig.VERSION_CODE
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENT_RUNNER
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //UI
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(Dependencies.SHIMMER)
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    // Navigation Component
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)

    //Paging
    implementation(Dependencies.PAGING_RUNTIME)
    implementation(Dependencies.PAGING_RX_JAVA)

    //Networking
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_JAVA)
    implementation(Dependencies.CONVERTER_GSON)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_INTERCEPTOR)

    //Dependency Injection
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_ANDROIDX_COMPILER)

    //Local Database
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_RX_JAVA)
    implementation(Dependencies.ROOM_PAGING)
    kapt(Dependencies.ROOM_COMPILER)

    // RxJava
    implementation(Dependencies.RX_JAVA)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.RX_BINDING)

    //Testing
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROIDX_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)

}
