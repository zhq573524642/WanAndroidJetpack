package com.zhq.wanandroidjetpack.api

import com.zhq.commonlib.net.BaseResponse
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean
import com.zhq.wanandroidjetpack.ui.home.bean.BannerData
import com.zhq.wanandroidjetpack.ui.home.bean.SearchHotWords
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/3 15:06
 * Description
 */
interface HomeApi {
    //获取首页Banner
    @GET("banner/json")
    suspend fun getBannerData(): BaseResponse<List<BannerData>>

    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getArticleList(
        @Path("page") page: Int,
        @Query("page_size") page_size: Int
    ): BaseResponse<CommonPageBean<CommonArticleItem>>


    //搜索热词
    @GET("hotkey/json")
    suspend fun getSearchHotWords(): BaseResponse<List<SearchHotWords>>

    //常用网站
    @GET("friend/json")
    suspend fun getCommonWebSite(): BaseResponse<List<SearchHotWords>>

}