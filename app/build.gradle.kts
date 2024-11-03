plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.expenseloom.personal.finance.manager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.expenseloom.personal.finance.manager"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //todo: Dependency Injection - Dagger HILT
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //todo: LifeCycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //todo: SDP & Ssp
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    testImplementation(libs.androidx.runner)
    testImplementation(libs.core.testing)
    androidTestImplementation(libs.core.testing)

    //todo: Coroutines Code
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.mockito.inline)
    testImplementation(libs.turbine)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockito.core)

    /*todo: Navigation Components */
    implementation(libs.androidx.navigation.fragment.ktx)  // Views/Fragments integration
    implementation(libs.androidx.navigation.ui.ktx)
    androidTestImplementation(libs.androidx.navigation.testing)     // Testing Navigation
    /*todo: Navigation Components */

    //todo: Data Store
    implementation(libs.androidx.datastore) // Proto
    implementation(libs.androidx.datastore.preferences)

    //todo: WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    androidTestImplementation(libs.androidx.work.testing)

    //todo: Room DB
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing) // Test
    implementation(libs.androidx.room.paging) // Paging 3

    //todo:  CameraX core library using the camera2 implementation
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle) // Lifecycle
    implementation(libs.androidx.camera.view)  // View class
    implementation(libs.androidx.camera.extensions) //Extension


    //todo: Image Loading Library
    implementation (libs.glide)

}