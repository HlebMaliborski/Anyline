private object SharedVersions {

    const val coroutines = "1.4.0"
    const val koin = "2.1.5"
    const val kotlin = "1.4.10"
}

object AppVersion {

    const val versionName = "1.0.0"
    const val versionCode = 1
}

object AndroidVersions {

    const val minSdkVersion = 22
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val buildToolsVersion = "30.0.2"
}

object Plugins {
    private object Versions {

        const val androidGradle = "4.0.0"
        const val junit5 = "1.6.0.0"
        const val googleServices = "4.3.4"
    }

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junit5}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${SharedVersions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
}

object Dependencies {
    object Versions {

        const val androidX = "1.1.0"
        const val material = "1.2.1"
        const val constraintLayout = "1.1.3"
        const val lifecycleExtensions = "2.2.0"
        const val lifecycleRuntime = "2.3.0-alpha07"
        const val lifecycleViewModel = "2.3.0-alpha07"
        const val navKtx = "2.3.0"
        const val recyclerView = "1.1.0"
        const val glide = "4.11.0"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.androidX}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${SharedVersions.coroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${SharedVersions.coroutines}"
    const val koinAndroidScopes = "org.koin:koin-androidx-scope:${SharedVersions.koin}"
    const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:${SharedVersions.koin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${SharedVersions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${SharedVersions.kotlin}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navKtx}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navKtx}"
    const val runtimeExtensions = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleExtensions}"
    const val viewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleExtensions}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object TestDependencies {
    private object Versions {

        const val androidxCoreTesting = "2.1.0"
        const val androidxTest = "1.2.0"
        const val equalsVerifier = "3.1.13"
        const val espresso = "3.2.0"
        const val junit = "1.1.1"
        const val junit5 = "5.4.2"
        const val kotlinTestDsl = "0.1.5"
        const val mannodermausJunit5 = "1.2.0"
        const val mockk = "1.9.3"
    }

    const val androidxCoreTesting = "androidx.arch.core:core-testing:${Versions.androidxCoreTesting}"
    const val androidxTestCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"
    const val androidxTestRules = "androidx.test:rules:${Versions.androidxTest}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${SharedVersions.coroutines}"
    const val equalsVerifier = "nl.jqno.equalsverifier:equalsverifier:${Versions.equalsVerifier}"
    const val junit = "androidx.test.ext:junit:${Versions.junit}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
    const val koinTest = "org.koin:koin-test:${SharedVersions.koin}"
    const val kotlinTestDsl = "com.mapbox.test.dsl:mapbox-kotlin-test-dsl:${Versions.kotlinTestDsl}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}
