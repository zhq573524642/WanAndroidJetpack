package com.zhq.wanandroidjetpack.ui.projects.viewmodel

import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.repo.ProjectsRepo

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 15:09
 * Description
 */
class ProjectDetailViewModel(private val repo: ProjectsRepo) : BaseViewModel() {

    val projectList: ResponseStateData<CommonPageBean<CommonArticleItem>>
        get() = _projectList
    private val _projectList = ResponseStateData<CommonPageBean<CommonArticleItem>>()

    fun getProjectList(page: Int, cid: Int) = launch {
        repo.getProjectList(page, cid, _projectList)
    }

}