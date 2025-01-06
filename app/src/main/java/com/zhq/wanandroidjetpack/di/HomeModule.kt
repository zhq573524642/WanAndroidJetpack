package com.zhq.wanandroidjetpack.di

import com.zhq.commonlib.net.RetrofitManager
import com.zhq.wanandroidjetpack.api.HomeApi
import com.zhq.wanandroidjetpack.repo.HomeRepo
import com.zhq.wanandroidjetpack.repo.SearchRepo
import com.zhq.wanandroidjetpack.ui.home.viewmodel.HomeViewModel
import com.zhq.wanandroidjetpack.ui.home.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/3 15:03
 * Description
 */
val homeModule = module() {
    single { RetrofitManager.create<HomeApi>() }
    single { HomeRepo(get(HomeApi::class.java)) }
    single { SearchRepo(get(HomeApi::class.java)) }
    viewModel { HomeViewModel(get(HomeRepo::class.java)) }
    viewModel { SearchViewModel(get(SearchRepo::class.java)) }
}