package com.zhq.wanandroidjetpack.ui.home.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zhq.commonlib.base.BaseActivity
import com.zhq.commonlib.net.BaseStateObserver
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ActivityHomeSearchBinding
import com.zhq.wanandroidjetpack.ui.home.adapter.SearchHotWordsAdapter
import com.zhq.wanandroidjetpack.ui.home.bean.SearchHotWords
import com.zhq.wanandroidjetpack.ui.home.viewmodel.SearchViewModel
import com.zhq.wanandroidjetpack.ui.web.WebActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchActivity : BaseActivity<ActivityHomeSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModel()
    lateinit var hotWordsAdapter: SearchHotWordsAdapter
    lateinit var webSiteAdapter: SearchHotWordsAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_home_search
    }

    override fun initView() {
        mBinding.btnClose.setOnClickListener { finish() }
        initHotSearchWords()
        initCommonWebSite()
        initData()
        searchViewModel.getSearchHotWords()
        searchViewModel.getCommonWebSite()
    }

    private fun initHotSearchWords() {
        mBinding.rvSearchHot.isNestedScrollingEnabled = false
        mBinding.rvSearchHot.layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
        hotWordsAdapter = SearchHotWordsAdapter()
        mBinding.rvSearchHot.adapter = hotWordsAdapter
        hotWordsAdapter.setItemClick { position: Int ->
            mBinding.searchView.etInput.setText(hotWordsAdapter.getItem(position).name)
        }
    }

    private fun initCommonWebSite() {
        mBinding.rvCommonSite.isNestedScrollingEnabled = false
        mBinding.rvCommonSite.layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
        webSiteAdapter = SearchHotWordsAdapter()
        mBinding.rvCommonSite.adapter = webSiteAdapter
        webSiteAdapter.setItemClick { position: Int ->
            val item = webSiteAdapter.getItem(position)
            startActivityForParams<WebActivity>(mContext) {
                putExtra("web_url", item.link)
                putExtra("web_title", item.name)
            }
        }

    }

    private fun initData() {
        searchViewModel.hotWordsList.observe(this,
            object : BaseStateObserver<List<SearchHotWords>>() {
                override fun getRespDataSuccess(it: List<SearchHotWords>) {
                    hotWordsAdapter.setData(it)
                    hotWordsAdapter.notifyDataSetChanged()
                }
            })
        searchViewModel.webSiteList.observe(
            this,
            object : BaseStateObserver<List<SearchHotWords>>() {
                override fun getRespDataSuccess(it: List<SearchHotWords>) {
                    webSiteAdapter.setData(it)
                    webSiteAdapter.notifyDataSetChanged()
                }
            })
    }


}