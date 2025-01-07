package com.zhq.wanandroidjetpack.ui.projects.viewmodel

import androidx.fragment.app.Fragment
import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.wanandroidjetpack.repo.ProjectsRepo
import com.zhq.wanandroidjetpack.ui.projects.bean.ProjectType

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 14:00
 * Description
 */
class ProjectsViewModel(private val repo: ProjectsRepo) : BaseViewModel() {
    var fragmentList: ArrayList<Fragment> = arrayListOf()
    val projectTypeList: ResponseStateData<List<ProjectType>>
        get() = _projectTypeList
    private val _projectTypeList = ResponseStateData<List<ProjectType>>()

    fun getProjectsType() = launch {
        repo.getProjectsType(_projectTypeList)
    }
}