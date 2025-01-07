package com.zhq.wanandroidjetpack.ui.home.viewmodel

import com.zhq.commonlib.base.BaseViewModel
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.wanandroidjetpack.repo.HomeRepo
import com.zhq.wanandroidjetpack.repo.SearchRepo
import com.zhq.wanandroidjetpack.ui.home.bean.SearchHotWords

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 14:57
 * Description
 */
class SearchViewModel(private val repo: HomeRepo) : BaseViewModel() {
    val hotWordsList: ResponseStateData<List<SearchHotWords>>
        get() = _hotWordsList
    private val _hotWordsList = ResponseStateData<List<SearchHotWords>>()

    val webSiteList: ResponseStateData<List<SearchHotWords>>
        get() = _webSiteList

    private val _webSiteList = ResponseStateData<List<SearchHotWords>>()

    fun getSearchHotWords() = launch {
        repo.getSearchHotWords(_hotWordsList)
    }

    fun getCommonWebSite() = launch {
        repo.getCommonWebSite(_webSiteList)
    }
}