# Jetpack-Fly

## Wiki

[详细使用方法及扩展功能，请参照 Wiki (开发前必看!!!)](https://github.com/tiamosu/Jetpack-Fly/wiki)

## Requirements

适用于 Android 5.0 + (21 + API级别) 和 Java 11 +。

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }

        // sonatype
        maven { url "https://s01.oss.sonatype.org/content/groups/public" }
        maven { url "https://s01.oss.sonatype.org/content/repositories/releases" }
    }
}
```

### jfly-core（必选）

```groovy
implementation 'com.gitee.tiamosu:jfly-core:1.0.7'
```

### jfly-navigation（可选）

```groovy
implementation 'com.gitee.tiamosu:jfly-navigation:1.0.7'
```

如使用 kotlin 拓展，在上述基础上，添加如下依赖即可：

```groovy
implementation("androidx.navigation:navigation-fragment-ktx:2.5.0") {
    exclude group: 'androidx.navigation', module: "navigation-fragment"
}
```

### jfly-viewbinding（可选）

```groovy
implementation 'com.gitee.tiamosu:jfly-viewbinding:1.0.7'

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}
```

### jfly-core 模块依赖

```groovy
dependencies {
    //androidx
    api Deps.appcompat
    api Deps.fragment_ktx
    api Deps.core_ktx

    //lifecycle
    api Deps.lifecycle_runtime_ktx
    api Deps.lifecycle_livedata_ktx
    api Deps.lifecycle_viewmodel_ktx

    //utilcode
    api Deps.utilcode
}
```

### jfly-navigation 模块依赖

```groovy
dependencies {
    implementation project(path: ':fly-core')
    api(Deps.navigation_fragment_ktx) {
        exclude group: 'androidx.navigation', module: "navigation-fragment"
    }
}
```

### jfly-viewbinding 模块依赖

```groovy
dependencies {
    implementation project(path: ':fly-core')
    implementation Deps.recyclerview
}
```

## proguard

本框架已配置混淆，工程会自动传递混淆，使用时无需另行配置。

jfly-core: [android-rules.pro](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/consumer-rules.pro)
jfly-viewbinding：[consumer-rules.pro](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-viewbinding/consumer-rules.pro)

## [更新日志](https://github.com/tiamosu/Jetpack-Fly/blob/master/CHANGELOG.md)

## *特别感谢*

* [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
* [AndroidProject](https://github.com/getActivity/AndroidProject)
* [Jetpack-MVVM-Best-Practice](https://github.com/KunMinX/Jetpack-MVVM-Best-Practice)
* [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)