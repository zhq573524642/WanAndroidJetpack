package com.zhq.wanandroidjetpack.di

import com.zhq.commonlib.net.RetrofitManager
import com.zhq.wanandroidjetpack.api.HarmonyApi
import com.zhq.wanandroidjetpack.repo.HarmonyRepo
import com.zhq.wanandroidjetpack.ui.harmony.viewmodel.HarmonyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 10:59
 * Description
 */
val harmonyModule = module {
    single { RetrofitManager.create<HarmonyApi>() }
    single { HarmonyRepo(get()) }
    viewModel { HarmonyViewModel(get()) }
}