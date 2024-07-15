plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.worldofplantsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.worldofplantsapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    /** addiction **/
    implementation(project(":data"))
    implementation(project(":domain"))

    /** Room **/
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.guava)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    /**ImmutableList**/
    implementation(libs.kotlinx.collections.immutable)

    /** Retrofit **/
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    /** bottom animated bar **/
    implementation(libs.animated.navigation.bar)

    /** Navigation **/
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.accompanist.navigation.animation.vlatestversion)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.accompanist.navigation.animation.v0230)
    implementation(libs.accompanist.navigation.animation)

    /** For swipeable state **/
    implementation(libs.androidx.material)

    /** UI **/
    implementation(libs.accompanist.systemuicontroller)

    /** Hilt **/
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose.v120)

    /** Firebase **/
    implementation(libs.google.firebase.crashlytics.buildtools)

    /** Coil **/
    implementation(libs.coil.compose)

    /** Lottie animation **/
    implementation(libs.lottie.compose)

    /** material **/
    implementation(libs.material)

    /** work manager **/
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.accompanist.flowlayout)
    implementation(libs.androidx.foundation)
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
}
