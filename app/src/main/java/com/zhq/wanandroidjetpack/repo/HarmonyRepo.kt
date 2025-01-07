package com.zhq.wanandroidjetpack.repo

import com.zhq.commonlib.base.BaseRepository
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.wanandroidjetpack.api.HarmonyApi
import com.zhq.wanandroidjetpack.api.HomeApi
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 18:04
 * Description
 */
class HarmonyRepo(private val  api:HarmonyApi) :BaseRepository() {

   suspend fun getHarmonyData(data:ResponseStateData<HarmonyBean>)=handleResponse({
      api.getHarmonyData()
   },data)
}