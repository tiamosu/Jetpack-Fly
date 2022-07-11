package com.tiamosu.fly.viewbinding.kts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.reflect.Method

/**
 * @author ti
 * @date 2022/7/11.
 */

object ReflectKt {
    private const val METHOD_BIND = "bind"
    private const val METHOD_INFLATE = "inflate"

    fun <T> getBindMethod(clazz: Class<T>): Method {
        return clazz.getMethod(METHOD_BIND, View::class.java)
    }

    fun <T> getInflateMethod(clazz: Class<T>): Method {
        return clazz.getMethod(METHOD_INFLATE, LayoutInflater::class.java)
    }

    fun <T> getInflateMethod2(clazz: Class<T>): Method {
        return clazz.getMethod(METHOD_INFLATE, LayoutInflater::class.java, ViewGroup::class.java)
    }

    fun <T> getInflateMethod3(clazz: Class<T>): Method {
        return clazz.getMethod(
            METHOD_INFLATE,
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
    }
}

fun <T> Class<T>.bindMethod() = ReflectKt.getBindMethod(this)

fun <T> Class<T>.inflateMethod() = ReflectKt.getInflateMethod(this)

fun <T> Class<T>.inflateMethod2() = ReflectKt.getInflateMethod2(this)

fun <T> Class<T>.inflateMethod3() = ReflectKt.getInflateMethod3(this)