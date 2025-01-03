package com.zhq.wanandroidjetpack.repo

import com.zhq.commonlib.base.BaseRepository
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.api.HomeApi
import com.zhq.wanandroidjetpack.ui.home.bean.BannerData

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:35
 * Description
 */
class HomeRepo(private val api: HomeApi) : BaseRepository() {

    //获取首页Banner
    suspend fun getBannerData(data: ResponseStateData<List<BannerData>>) = handleResponse({
        api.getBannerData()
    }, data)

    suspend fun getArticle(
        currentPage: Int,
        data: ResponseStateData<CommonPageBean<CommonArticleItem>>,
        isLoadCache: Boolean = true
    ) =
        handleResponse(
            {
                api.getArticleList(currentPage, 10)
            }, data
        )
}

