dependencies {
    "implementation"(Dependencies.kotlin)
    "implementation"(Dependencies.appCompat)
    "implementation"(Dependencies.material)
    "implementation"(Dependencies.coreKtx)
    "implementation"(Dependencies.koinAndroidViewModel)
    "implementation"(Dependencies.koinAndroidScopes)
    "implementation"(Dependencies.coroutinesCore)
    "implementation"(Dependencies.coroutinesAndroid)
    "implementation"(Dependencies.kotlinReflect)

    "testImplementation"(TestDependencies.junit)
    "testImplementation"(Dependencies.kotlinReflect)
    "testImplementation"(TestDependencies.koinTest)
    "testImplementation"(TestDependencies.mockk)
    "testImplementation"(TestDependencies.coroutinesTest)
}
