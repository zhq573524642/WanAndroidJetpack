package com.zhq.wanandroidjetpack.di

import com.zhq.commonlib.net.RetrofitManager
import com.zhq.wanandroidjetpack.api.ProjectsApi
import com.zhq.wanandroidjetpack.repo.ProjectsRepo
import com.zhq.wanandroidjetpack.ui.projects.viewmodel.ProjectDetailViewModel
import com.zhq.wanandroidjetpack.ui.projects.viewmodel.ProjectsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 13:59
 * Description
 */
val projectModule = module {
    single { RetrofitManager.create<ProjectsApi>() }
    single { ProjectsRepo(get()) }
    viewModel { ProjectsViewModel(get()) }
    viewModel { ProjectDetailViewModel(get(ProjectsRepo::class.java)) }
}