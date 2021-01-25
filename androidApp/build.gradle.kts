plugins {
    id("com.android.application")
    kotlin("android")
}

repositories {
    google()
    jcenter()
}

apply(from = "../dependencies.gradle")

dependencies {
    implementation(project(":domain"))
    implementation(project(":local"))
    implementation(deps.getValue("material"))
    implementation(deps.getValue("kodeinAndroid"))
    implementation(deps.getValue("livedata"))
    implementation(deps.getValue("composeLivedata"))
    implementation(deps.getValue("uiTooling"))
    implementation(deps.getValue("composeFoundation"))
    implementation(deps.getValue("composeMaterial"))
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.github.wzieba.songapp.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-alpha05"
        kotlinCompilerVersion = "1.4.10"
    }
}
