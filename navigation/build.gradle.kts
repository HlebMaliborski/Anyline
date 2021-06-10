plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
}

dependencies {

    implementation(Dependencies.navFragmentKtx)
    implementation(Dependencies.navUiKtx)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    apply(from = "$rootDir/gradle/base-dependencies.gradle.kts")
}
