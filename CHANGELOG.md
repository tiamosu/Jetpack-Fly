# 新版本 1.2.5

* fragment:1.5.3
* navigation-fragment-ktx:2.5.3

# 新版本 1.2.4

* databinding_runtime = "7.3.1"
* Activity 新增 initCreate 函数

# 新版本 1.2.3

* 优化：点击空白区域，默认隐藏软键盘

# 新版本 1.2.2

* fragment 1.5.3
* 修改 gradle 对 dataBinding 的引用

# 新版本 1.2.0

* Fragment 可见性调整，新增onSupportVisible、onSupportInvisible函数及isSupportVisible可见性判断

# 新版本 1.1.9

* 移除 utilcode 第三方库
* 新增 appContext 全局上下文
* 新增 ActivityUtils、BarUtils、DebouncingUtils、KeyboardUtils、ViewUtils 等工具类

# 新版本 1.1.8

* appcompat = "1.5.1"
* navigation_runtime = "2.5.2"
* 新增 mvi-dispatcher 使用案例
* 移除 unpeek_livedata、mavericks 使用案例

# 新版本 1.1.7

* appcompat:1.5.0
* fragment:1.5.2
* 删除 IAppViewModel，全局 ViewModel 实现使用 ApplicationInstance 替换

# 新版本 1.1.6

* 移除 <uses-sdk tools:overrideLibrary="com.kunminx.unpeeklivedata" />

# 新版本 1.1.5

* 新增相关属性配置：

```xml

<uses-sdk tools:overrideLibrary="com.kunminx.unpeeklivedata" />

<queries>
<!-- Android 11 软件包可见性适配 -->

<!-- 调起其他页面意图：Intent.ACTION_VIEW -->
<intent>
    <action android:name="android.intent.action.VIEW" />
</intent>
</queries>

<application android:hasFragileUserData="true"
android:largeHeap="true"
android:networkSecurityConfig="@xml/fly_network_security_config"
android:requestLegacyExternalStorage="true"
tools:ignore="UnusedAttribute">

<!-- 适配全面屏 vivo & oppo-->
<meta-data android:name="android.max_aspect" android:value="2.4" />

<!-- 适配刘海屏、水滴屏 小米 -->
<meta-data android:name="notch.config" android:value="portrait|landscape" />

<!-- 适配刘海屏、水滴屏 华为 -->
<meta-data android:name="android.notch_support" android:value="true" />
</application>
```

# 新版本 1.1.4

* 防抖默认时间0.5秒改为1秒

# 新版本 1.1.3

* fragment → 1.5.1
* navigation → 2.5.1
* lifecycle → 2.5.1
* 添加kotlin混淆配置

# 新版本 1.1.1

* FlySupportDialogFragment 新增 onDialogCancel、onDialogDismiss 函数

# 新版本 1.1.0

* Fragment onBackPressedSupport 回退判断优化

# 新版本 1.0.9

* Fragment onBackPressedSupport 回退判断优化

# 新版本 1.0.8

* 新增 FlySupportDialogFragment

# 新版本 1.0.7

* 添加混淆配置