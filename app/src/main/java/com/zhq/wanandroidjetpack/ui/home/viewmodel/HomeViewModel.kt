package com.zhq.wanandroidjetpack.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.repo.HomeRepo
import com.zhq.wanandroidjetpack.ui.home.bean.BannerData

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:36
 * Description
 */
class HomeViewModel(private val repo: HomeRepo) : BaseViewModel() {
    val bannerList: ResponseStateData<List<BannerData>>
        get() = _bannerList
    private val _bannerList = ResponseStateData<List<BannerData>>()

    val article: ResponseStateData<CommonPageBean<CommonArticleItem>>
        get() = _article
    private val _article = ResponseStateData<CommonPageBean<CommonArticleItem>>()


    fun getArticle(currentPage: Int) = launch {
        repo.getArticle(currentPage, _article)
    }

    fun getBannerData() = launch {
        repo.getBannerData(_bannerList)
    }


}