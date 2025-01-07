package com.zhq.wanandroidjetpack.ui.harmony.viewmodel

import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.wanandroidjetpack.repo.HarmonyRepo
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 18:03
 * Description
 */
class HarmonyViewModel(private val repo: HarmonyRepo) : BaseViewModel() {

    val data: ResponseStateData<HarmonyBean>
        get() = _data
    private val _data = ResponseStateData<HarmonyBean>()

    fun getHarmonyData() = launch {
        repo.getHarmonyData(_data)
    }
}