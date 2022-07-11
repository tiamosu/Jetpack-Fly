@file:Suppress("unused", "SpellCheckingInspection")

object Android {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32

    const val versionName = "1.0.0"
    const val versionCode = 10000
}

object Versions {
    const val kotlin = "1.7.10"
    const val utilcode = "1.31.0"
    const val immersionbar = "3.2.2"
}

object Deps {
    //kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    //androidx
    const val core_ktx = "androidx.core:core-ktx:1.8.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.2"
    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"

    //navigation
    const val fragment = "androidx.fragment:fragment:1.5.0"
    const val navigation_runtime = "androidx.navigation:navigation-runtime:2.5.0"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:2.5.0"

    //lifecycle
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0"
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0"

    //utilcode：常用工具类库 https://github.com/Blankj/AndroidUtilCode
    const val utilcode = "com.blankj:utilcodex:${Versions.utilcode}"

}