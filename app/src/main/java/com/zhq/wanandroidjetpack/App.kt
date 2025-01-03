package com.zhq.wanandroidjetpack

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.core.os.BuildCompat
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.zhq.commonlib.statelayout.CommonStateProvider
import com.zhq.commonlib.statelayout.source.StateLayout
import com.zhq.commonlib.utils.LogUtil
import com.zhq.commonlib.utils.SpUtils
import com.zhq.commonlib.utils.ToastUtils
import com.zhq.wanandroidjetpack.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/2 16:09
 * Description
 */
class App :Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }
    private val modules = mutableListOf(homeModule)
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(
                context: Context,
                layout: RefreshLayout
            ): RefreshHeader {
                layout.setPrimaryColorsId(R.color.color_f5f5f5,R.color.black)//全局设置主题颜色
                return ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"))//指定为经典Header，默认是 贝塞尔雷达Header
            }

        })

        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(
                context: Context,
                layout: RefreshLayout
            ): RefreshFooter {
                //指定为经典Footer，默认是 BallPulseFooter
                return ClassicsFooter(context).setDrawableSize(20f)
            }

        })
    }


    override fun onCreate() {
        super.onCreate()
        mContext=applicationContext
        initKoin()
        ToastUtils.init(this)
        SpUtils.init(this)
        LogUtil.setLogLevel(LogUtil.DEBUG)
        LogUtil.setDebug(false)
        StateLayout.setGlobalStateProvider(CommonStateProvider())
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(modules)
        }
    }
}