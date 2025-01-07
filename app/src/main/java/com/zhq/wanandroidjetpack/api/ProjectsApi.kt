package com.zhq.wanandroidjetpack.api

import com.zhq.commonlib.net.BaseResponse
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.ui.projects.bean.ProjectType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 13:55
 * Description
 */
interface ProjectsApi {

    /**
     * 获取项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectsType(): BaseResponse<List<ProjectType>>

    /**
     * 获取项目列表
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectsList(@Path("page") page:Int,@Query("cid") cid:Int):BaseResponse<CommonPageBean<CommonArticleItem>>
}