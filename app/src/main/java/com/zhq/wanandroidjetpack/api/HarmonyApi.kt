package com.zhq.wanandroidjetpack.api

import com.zhq.commonlib.net.BaseResponse
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean
import retrofit2.http.GET

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 11:00
 * Description
 */
interface HarmonyApi {
    //鸿蒙
    @GET("harmony/index/json")
    suspend fun getHarmonyData(): BaseResponse<HarmonyBean>
}