plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
}
android {
  namespace = "com.protohax.mobile"
  compileSdk = 34
  defaultConfig {
    applicationId = "com.protohax.mobile"
    minSdk = 28; targetSdk = 34; versionCode = 1; versionName = "0.1.0"
    ndk { abiFilters += setOf("arm64-v8a", "armeabi-v7a") }
  }
  buildFeatures { buildConfig = true; compose = true }
  buildTypes {
    debug { isMinifyEnabled = false }
    release { isMinifyEnabled = true; proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro") }
  }
  compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
}
kotlin { compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) } }
dependencies {
  implementation(project(":relay"))
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.7.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
  implementation("androidx.activity:activity-compose:1.9.2")
  implementation(platform("androidx.compose:compose-bom:2024.06.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.navigation:navigation-compose:2.7.7")
}
