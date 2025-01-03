package com.zhq.commonlib.utils

import android.util.Log

/**
 * @Author ZhangHuiQiang
 * @Date 2023/12/19 14:08
 * Description
 */
object LogUtil {

    const val VERBOSE = 1
    const val DEBUG = 2
    const val INFO = 3
    const val WARN = 4
    const val ERROR = 5
    private var level = VERBOSE
    private var isDebug = true

    fun setLogLevel(level: Int) {
        this.level = level
    }

    fun setDebug(isDebug: Boolean) {
        this.isDebug = isDebug
    }

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE && isDebug) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG && isDebug) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO && isDebug) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN && isDebug) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR && isDebug) {
            Log.e(tag, msg)
        }
    }
}