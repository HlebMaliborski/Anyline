buildscript {
    val kotlin_version by extra("1.4.30")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
        classpath(Plugins.androidGradle)
        classpath(Plugins.kotlinGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://anylinesdk.blob.core.windows.net/maven/")
        google()
        jcenter()
    }
}

subprojects {

    plugins.withType<com.android.build.gradle.LibraryPlugin> {
        configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(AndroidVersions.compileSdkVersion)
            defaultConfig {
                minSdkVersion(AndroidVersions.minSdkVersion)
                targetSdkVersion(AndroidVersions.targetSdkVersion)
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildTypes {
                getByName("debug") {
                    isTestCoverageEnabled = true
                }
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            testOptions {
                unitTests.all(closureOf<Test> { maxHeapSize = "1G" } as groovy.lang.Closure<Test>)
            }
        }
    }

    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper> {
        this@subprojects.run {
            tasks {
                withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}