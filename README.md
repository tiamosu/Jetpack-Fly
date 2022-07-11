# Jetpack-Fly

## Gradle 引入

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()

        //或者 sonatype
        maven { url "https://s01.oss.sonatype.org/content/groups/public" }
        maven { url "https://s01.oss.sonatype.org/content/repositories/releases" }
    }
}
```

```groovy
implementation 'com.gitee.tiamosu:jfly-core:1.0.1'  //（必选）
implementation 'com.gitee.tiamosu:jfly-navigation:1.0.1'    //（可选）
```

## *特别感谢*

* [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
* [AndroidProject](https://github.com/getActivity/AndroidProject)
* [Jetpack-MVVM-Best-Practice](https://github.com/KunMinX/Jetpack-MVVM-Best-Practice)
* [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)