package com.zhq.commonlib.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 14:20
 * Description
 */
@SuppressLint("StaticFieldLeak")
object SpUtils {
    lateinit var mContext: Context

    fun init(application: Application) {
        mContext = application.applicationContext
    }

    private fun getSpEditor(): SharedPreferences.Editor {
        if (mContext == null) {
            throw NullPointerException("Please call init in your Application onCreate")
        }
        return mContext.getSharedPreferences("My_SharePf", Context.MODE_PRIVATE).edit()
    }

    private fun getSp(): SharedPreferences {
        if (mContext == null) {
            throw NullPointerException("Please call init in your Application onCreate")
        }
        return mContext.getSharedPreferences("My_SharePf", Context.MODE_PRIVATE)
    }

    fun putInt(key: String, value: Int) {
        getSpEditor().putInt(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        getSpEditor().putFloat(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        getSpEditor().putLong(key, value).apply()
    }

    fun putString(key: String, value: String) {
        getSpEditor().putString(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        getSpEditor().putBoolean(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return getSp().getInt(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return getSp().getFloat(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return getSp().getLong(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String = ""): String? {
        return getSp().getString(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false) {
        getSp().getBoolean(key, defaultValue)
    }

    fun removeKey(key: String) {
        getSpEditor().remove(key)
    }

    fun clearAll() {
        getSpEditor().clear()
    }

    fun isContainsKey(key: String): Boolean {
        return getSp().contains(key)
    }
}