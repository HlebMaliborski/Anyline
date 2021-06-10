plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
}
androidExtensions {
    isExperimental = true
}
dependencies {
    implementation(project(":base"))
    implementation(project(":navigation"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("io.coil-kt:coil:1.2.1")
    implementation("io.coil-kt:coil-svg:1.2.0")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")

    //Okhttp3
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("androidx.paging:paging-runtime-ktx:3.0.0")
    implementation(TestDependencies.jupiterApi)


    apply(from = "$rootDir/gradle/base-dependencies.gradle.kts")
}
