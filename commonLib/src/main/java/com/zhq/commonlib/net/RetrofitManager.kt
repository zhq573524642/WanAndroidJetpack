package com.zhq.commonlib.net

import android.os.Build
import android.util.Log
import com.zhq.commonlib.base.Constants

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 11:41
 * Description
 */
object RetrofitManager {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private val retrofit: Retrofit

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            Log.d("OkHttp Log：", it)
        }.setLevel(if (Constants.isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient().newBuilder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
//            .cookieJar(MyCookieJar())
            .addInterceptor(httpLoggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getApiService(service: Class<T>): T {
        return retrofit.create(service)
    }

    inline fun <reified T> create(): T = getApiService(T::class.java)

}