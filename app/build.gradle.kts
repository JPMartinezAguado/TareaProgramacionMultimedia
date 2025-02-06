import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jpmartineza.tareaprogramacionmultimedia"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jpmartineza.tareaprogramacionmultimedia"
        minSdk = 26
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {

    implementation(libs.androidx.navigation.compose)
    //implementation(libs.mediation.test.suite)
    //tarea 2, parte 2, instalacion de dependencias
    val room_version = "2.6.1"


    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)
    //fin tarea 2, parte 2

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

     //retrofit
    implementation (libs.squareup.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.jakewharton.retrofit2.kotlinx.serialization.converter)

    // nuevas dependencias para Retrofit y Kotlinx Serialization
    implementation (libs.squareup.retrofit)
    implementation (libs.kotlinx.serialization.json)
    implementation (libs.jakewharton.retrofit2.kotlinx.serialization.converter)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.squareup.retrofit)
    implementation (libs.kotlinx.serialization.json.v151)

    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.navigation.compose.v242)
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
    implementation (libs.kotlinx.serialization.json)
    implementation (libs.retrofit2.kotlinx.serialization.converter.v100)
    implementation (libs.squareup.retrofit)


}
