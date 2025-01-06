package com.zhq.wanandroidjetpack.ui.web

import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhq.commonlib.base.BaseActivity
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ActivityWebBinding

class WebActivity : BaseActivity<ActivityWebBinding>() {
    lateinit var webUrl: String
    lateinit var webTitle: String
    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun initView() {
        webUrl = intent?.getStringExtra("web_url").toString()
        webTitle = intent?.getStringExtra("web_title").toString()
        showTitle(webTitle)
        initWebView()
        if (!TextUtils.isEmpty(webUrl)) {
            mBinding.webView.loadUrl(webUrl)
        }
    }

    private fun initWebView() {
        mBinding.webView.webViewClient = MyWebViewClient()
        mBinding.webView.webChromeClient = WebChromeClient()
    }


    inner class MyWebViewClient : WebViewClient() {

    }

    inner class MyWebChromeClient : WebChromeClient() {

    }
}