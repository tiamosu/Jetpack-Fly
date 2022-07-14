# Jetpack-Fly

## 1 开始准备

### 1.1 Maven 仓库

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

### 1.2 Maven 依赖

#### 1.2.1 jfly-core（必选）

```groovy
implementation 'com.gitee.tiamosu:jfly-core:1.0.5'
```

#### 1.2.2 jfly-navigation（可选）

```groovy
implementation 'com.gitee.tiamosu:jfly-navigation:1.0.5'
```

如使用 kotlin 拓展，在上述基础上，添加如下依赖即可：

```groovy
implementation("androidx.navigation:navigation-fragment-ktx:2.5.0") {
    exclude group: 'androidx.navigation', module: "navigation-fragment"
}
```

#### 1.2.3 jfly-viewbinding（可选）

```groovy
implementation 'com.gitee.tiamosu:jfly-viewbinding:1.0.5'

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}
```

## 2 如何使用

### 2.1 jfly-core 模块

#### 2.1.1 基础类使用

[FlySupportActivity](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/FlySupportActivity.kt)

```kotlin
abstract class FlySupportActivity : AppCompatActivity(), IFlySupportActivity {
    private val delegate by lazy { FlySupportActivityDelegate(this) }
    val startDelegate = ActivityResultDelegate(apply { })

    final override fun getContext() = this

    final override val bundle: Bundle?
        get() = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate()
    }

    /**
     * 设置布局视图
     */
    override fun setContentView(): View? {
        return delegate.setContentView()
    }

    /**
     * 相关函数初始化
     */
    override fun initActivity() {
        delegate.initActivity()
    }

    /**
     * 点击空白区域，默认隐藏软键盘
     */
    override fun clickBlankArea() {
        delegate.clickBlankArea()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    /**
     * 点击空白区域隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        delegate.dispatchTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议重写该函数，请使用 [onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        delegate.onBackPressed()
    }

    /**
     * 页面回退处理
     */
    override fun onBackPressedSupport() = false
}
```

[IFlySupportActivity](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/delegate/IFlySupportActivity.kt)

```kotlin
interface IFlySupportActivity : ScenesAction,
    BundleAction, KeyboardAction, HandlerAction, IFlyBackCallback {

    //设置布局视图
    fun setContentView(): View?

    //初始化
    fun initActivity()

    //点击空白区域，默认隐藏软键盘
    fun clickBlankArea()
}
```

[FlySupportFragment](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/FlySupportFragment.kt)

```kotlin
abstract class FlySupportFragment : Fragment(), IFlySupportFragment {
    private val delegate by lazy { FlySupportFragmentDelegate(this) }
    val startDelegate = ActivityResultDelegate(apply { })

    protected lateinit var activity: AppCompatActivity

    override fun getContext() = activity

    override val bundle: Bundle?
        get() = arguments

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delegate.onViewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setContentView(inflater, container, savedInstanceState)
    }

    /**
     * 设置布局视图
     */
    override fun setContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return delegate.setContentView(inflater, container)
    }

    /**
     * 相关函数初始化
     */
    override fun initFragment() {
        delegate.initFragment()
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return delegate.onCreateAnimation(enter, nextAnim)
    }

    override fun onDestroyView() {
        delegate.onDestroyView()
        super.onDestroyView()
    }

    /**
     * 页面回退处理
     */
    override fun onBackPressedSupport() = false
}
```

[IFlySupportFragment](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/delegate/IFlySupportFragment.kt)

```kotlin
interface IFlySupportFragment : ScenesAction,
    BundleAction, KeyboardAction, HandlerAction, IFlyBackCallback {

    //设置布局视图
    fun setContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    //初始化
    fun initFragment()
}
```

#### 2.1.2 公有Action

[ScenesAction](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/action/ScenesAction.kt)

```kotlin
interface ScenesAction {

    /**
     * 上下文
     */
    fun getContext(): Context

    /**
     * 布局Id
     */
    fun getLayoutId(): Int

    /**
     * 初始化参数，如：页面传参
     * 执行于[FlySupportActivity.onCreate] or [FlySupportFragment.onViewCreated]
     */
    fun initParameter(bundle: Bundle?)

    /**
     * 初始化视图
     * 执行于[initParameter]之后
     */
    fun initView()

    /**
     * 初始化事件，如：点击事件
     * 执行于[initView]之后
     */
    fun initEvent()

    /**
     * 初始化观察者，如：LiveData观察者
     * 执行于[initEvent]之后
     */
    fun initObserver()

    /**
     * 加载数据
     * 执行于[initObserver]之后
     */
    fun loadData()

    /**
     * 懒加载处理，页面可见时执行
     * 若是[FlySupportFragment]，并且保证在转场动画执行之后[FlySupportFragment.onCreateAnimation]
     */
    fun onLazyLoad()
}
```

[BundleAction](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/action/BundleAction.kt)

```kotlin
interface BundleAction {
    val bundle: Bundle?

    fun getInt(name: String, defaultValue: Int = 0): Int {
        return bundle?.getInt(name, defaultValue) ?: defaultValue
    }

    fun getLong(name: String, defaultValue: Long = 0): Long {
        return bundle?.getLong(name, defaultValue) ?: defaultValue
    }

    fun getFloat(name: String, defaultValue: Float = 0f): Float {
        return bundle?.getFloat(name, defaultValue) ?: defaultValue
    }

    fun getDouble(name: String, defaultValue: Double = 0.0): Double {
        return bundle?.getDouble(name, defaultValue) ?: defaultValue
    }

    fun getBoolean(name: String, defaultValue: Boolean = false): Boolean {
        return bundle?.getBoolean(name, defaultValue) ?: defaultValue
    }

    fun getString(name: String, defaultValue: String? = null): String? {
        return bundle?.getString(name) ?: defaultValue
    }

    fun <P : Parcelable> getParcelable(name: String): P? {
        return bundle?.getParcelable(name)
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : Serializable> getSerializable(name: String): S? {
        return bundle?.getSerializable(name) as? S
    }

    fun getStringArrayList(name: String): ArrayList<String>? {
        return bundle?.getStringArrayList(name)
    }

    fun getIntegerArrayList(name: String): ArrayList<Int>? {
        return bundle?.getIntegerArrayList(name)
    }
}
```

[KeyboardAction](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/action/KeyboardAction.kt)

```kotlin
interface HandlerAction {

    /**
     * 获取 Handler
     */
    val handler get() = HANDLER

    /**
     * 延迟执行
     */
    fun post(r: Runnable?): Boolean {
        r ?: return false
        return postDelayed(r, 0)
    }

    /**
     * 延迟一段时间执行
     */
    fun postDelayed(r: Runnable?, delayMillis: Long): Boolean {
        r ?: return false
        var uptimeMillis = delayMillis
        if (uptimeMillis < 0) {
            uptimeMillis = 0
        }
        return postAtTime(r, SystemClock.uptimeMillis() + uptimeMillis)
    }

    /**
     * 在指定的时间执行
     */
    fun postAtTime(r: Runnable?, uptimeMillis: Long): Boolean {
        r ?: return false
        // 发送和当前对象相关的消息回调
        return HANDLER.postAtTime(r, this, uptimeMillis)
    }

    /**
     * 移除单个消息回调
     */
    fun removeCallbacks(r: Runnable?) {
        r?.let { HANDLER.removeCallbacks(it) }
    }

    /**
     * 移除全部消息回调
     */
    fun removeCallbacks() {
        // 移除和当前对象相关的消息回调
        HANDLER.removeCallbacksAndMessages(this)
    }

    companion object {
        val HANDLER = Handler(Looper.getMainLooper())
    }
}
```

[HandlerAction](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/action/HandlerAction.kt)

```kotlin
interface HandlerAction {

    /**
     * 获取 Handler
     */
    val handler get() = HANDLER

    /**
     * 延迟执行
     */
    fun post(r: Runnable?): Boolean {
        r ?: return false
        return postDelayed(r, 0)
    }

    /**
     * 延迟一段时间执行
     */
    fun postDelayed(r: Runnable?, delayMillis: Long): Boolean {
        r ?: return false
        var uptimeMillis = delayMillis
        if (uptimeMillis < 0) {
            uptimeMillis = 0
        }
        return postAtTime(r, SystemClock.uptimeMillis() + uptimeMillis)
    }

    /**
     * 在指定的时间执行
     */
    fun postAtTime(r: Runnable?, uptimeMillis: Long): Boolean {
        r ?: return false
        // 发送和当前对象相关的消息回调
        return HANDLER.postAtTime(r, this, uptimeMillis)
    }

    /**
     * 移除单个消息回调
     */
    fun removeCallbacks(r: Runnable?) {
        r?.let { HANDLER.removeCallbacks(it) }
    }

    /**
     * 移除全部消息回调
     */
    fun removeCallbacks() {
        // 移除和当前对象相关的消息回调
        HANDLER.removeCallbacksAndMessages(this)
    }

    companion object {
        val HANDLER = Handler(Looper.getMainLooper())
    }
}
```

#### 2.1.3 Activity、Fragment 对于 Back键 的监听

[IFlyBackCallback](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-core/src/main/java/com/tiamosu/fly/delegate/IFlyBackCallback.kt)

```kotlin
override fun onBackPressedSupport(): Boolean {
    //do something
    return true
}
```

### 2.2 jfly-navigation 模块

此模块提供了 [NavHostFragment](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-navigation/src/main/java/androidx/navigation/fragment/NavHostFragment.kt)
修改版，支持 Navigation 中以 Add Hide 切换 Fragment，且通过 setMaxLifecycle 设置，实现 Fragment 的真实可见性 onResume 及
onPause

#### 2.2.1 [NavHostKt](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-navigation/src/main/java/com/tiamosu/fly/navigation/NavHostKt.kt)

提供相关扩展：navigate、navigateUp、popBackStack

#### 2.2.2 [NavigationKt](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-navigation/src/main/java/com/tiamosu/fly/navigation/NavigationKt.kt)

提供相关路由 API：start、startWithPopTo、pop、popTo

##### 2.2.2.1 启动 Fragment

```kotlin
/**
 * 启动新的 Fragment
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param singleTop 是否进行栈顶复用
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param builder [NavOptionsBuilder] 配置
 */
fun NavHostKt.start(
    @IdRes resId: Int,
    args: Bundle? = null,
    interval: Long = 500,
    singleTop: Boolean = false,
    navigatorExtras: Navigator.Extras? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    startNav(
        resId = resId,
        args = args,
        interval = interval,
        builder = builder,
        singleTop = singleTop,
        navigatorExtras = navigatorExtras
    )
}

// 在 ComponentActivity 和 Fragment 中使用：navigator.start()
```

```kotlin
/**
 * 启动新的 Fragment，并关闭 [popUpToId] 之上的 Fragments
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param inclusive 指定目标[popUpToId]是否也弹出
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param builder [NavOptionsBuilder] 配置
 */
fun NavHostKt.startWithPopTo(
    @IdRes resId: Int,
    popUpToId: Int,
    inclusive: Boolean = false,
    args: Bundle? = null,
    interval: Long = 500,
    navigatorExtras: Navigator.Extras? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    startNav(
        resId = resId,
        popUpToId = popUpToId,
        inclusive = inclusive,
        args = args,
        interval = interval,
        builder = builder,
        navigatorExtras = navigatorExtras
    )
}

// 在 ComponentActivity 和 Fragment 中使用：：navigator.startWithPopTo()
```

##### 2.2.2.2 出栈

```kotlin
/**
 * 出栈当前 Fragment
 */
fun NavHostKt.pop() {
    navigateUp()
}

// 在 ComponentActivity 和 Fragment 中使用：：navigator.pop()
```

```kotlin
/**
 * 出栈 [popUpToId] 之上的 Fragments
 *
 * @param popUpToId 退出栈到指定目标ID
 * @param inclusive 指定目标[popUpToId]是否也弹出
 */
fun NavHostKt.popTo(
    @IdRes popUpToId: Int,
    inclusive: Boolean = false
) {
    popBackStack(popUpToId, inclusive)
}

// 在 ComponentActivity 和 Fragment 中使用：：navigator.popTo()
```

#### 2.2.3 [NavAnimKt](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-navigation/src/main/java/com/tiamosu/fly/navigation/NavAnimKt.kt)

提供 Fragment 切换动画

### 2.3 jfly-viewbinding 模块

此模块提供了 [ViewBindingKt](https://github.com/tiamosu/Jetpack-Fly/blob/master/fly-viewbinding/src/main/java/com/tiamosu/fly/viewbinding/ViewBindingKt.kt)，获取
ViewBinding 和 DataBinding 实例的简单 API

#### 2.3.1 在 ComponentActivity 使用，[详情查看：](https://github.com/tiamosu/Jetpack-Fly/blob/master/app/src/main/java/com/tiamosu/fly/demo/ui/activity/MainActivity.kt)

```kotlin
// 获取 ViewBinding
private val binding by viewBinding<ActivityMainBinding>()

// 获取 DataBinding
private val binding by dataBinding<ActivityMainBinding>()
```

#### 2.3.2 在 Fragment 使用，[详情查看：](https://github.com/tiamosu/Jetpack-Fly/blob/master/app/src/main/java/com/tiamosu/fly/demo/ui/fragment/MainFragment.kt)

```kotlin
// 获取 ViewBinding
private val binding by viewBinding<FragmentMainBinding>()

// 获取 DataBinding
private val binding by dataBinding<FragmentMainBinding>()
```

#### 2.3.3 在 ViewGroup 使用，[详情查看：](https://github.com/tiamosu/Jetpack-Fly/blob/master/app/src/main/java/com/tiamosu/fly/demo/ui/view/CustomViewLayout.kt)

```kotlin
// 获取 ViewBinding
private val binding by viewBinding<LayoutCustomViewBinding>()

// 获取 DataBinding
private val binding by dataBinding<LayoutCustomViewBinding>()
```

#### 2.3.4 在 RecyclerView.ViewHolder 使用，[详情查看：](https://github.com/tiamosu/Jetpack-Fly/blob/master/app/src/main/java/com/tiamosu/fly/demo/ui/adapter/HomeAdapter.kt)

```kotlin
// 获取 ViewBinding
private val binding by viewBinding<ItemHomeBinding>()

// 获取 DataBinding
private val binding by dataBinding<ItemHomeBinding>()
```

#### 2.3.5 View 转 ViewBinding、DataBinding

```kotlin
// 获取 ViewBinding
private val binding = contentView.toViewBinding<LayoutCustomViewBinding>()

// 获取 DataBinding
private val binding = contentView.toDataBinding<LayoutCustomViewBinding>()
```

## *特别感谢*

* [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
* [AndroidProject](https://github.com/getActivity/AndroidProject)
* [Jetpack-MVVM-Best-Practice](https://github.com/KunMinX/Jetpack-MVVM-Best-Practice)
* [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)