package com.tiamosu.fly.action

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * @author ti
 * @date 2022/7/6.
 */
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