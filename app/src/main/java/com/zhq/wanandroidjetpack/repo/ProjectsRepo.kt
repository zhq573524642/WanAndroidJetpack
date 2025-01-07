package com.zhq.wanandroidjetpack.repo

import com.zhq.commonlib.base.BaseRepository
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.api.ProjectsApi
import com.zhq.wanandroidjetpack.ui.projects.bean.ProjectType

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 13:56
 * Description
 */
class ProjectsRepo(private val api:ProjectsApi) :BaseRepository() {

    //获取项目分类
    suspend fun getProjectsType(data:ResponseStateData<List<ProjectType>>)=handleResponse({
         api.getProjectsType()
    },data)

    //获取项目列表
    suspend fun getProjectList(page:Int,cid:Int,data:ResponseStateData<CommonPageBean<CommonArticleItem>>)=handleResponse({
        api.getProjectsList(page,cid)
    },data)
}