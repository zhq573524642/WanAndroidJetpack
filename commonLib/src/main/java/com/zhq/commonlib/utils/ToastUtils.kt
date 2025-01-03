package com.zhq.commonlib.utils

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:30
 * Description
 */
object ToastUtils {
    lateinit var mContext: Application
    fun init(application: Application) {
        mContext = application
    }

    fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(mContext.applicationContext, this, duration).show()
    }

    fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(mContext.applicationContext, this, duration).show()
    }
}