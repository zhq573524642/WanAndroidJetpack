package com.zhq.wanandroidjetpack.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.AlphaPageTransformer
import com.youth.banner.transformer.ScaleInTransformer
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.commonlib.net.BaseStateObserver
import com.zhq.commonlib.utils.ToastUtils.showToast
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.FragmentHomeBinding
import com.zhq.wanandroidjetpack.ui.home.adapter.HomeArticleAdapter
import com.zhq.wanandroidjetpack.ui.home.adapter.MyBannerAdapter
import com.zhq.wanandroidjetpack.ui.home.bean.BannerData
import com.zhq.wanandroidjetpack.ui.home.search.HomeSearchActivity
import com.zhq.wanandroidjetpack.ui.home.viewmodel.HomeViewModel
import com.zhq.wanandroidjetpack.ui.web.WebActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/2 16:55
 * Description
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    var bannerList: MutableList<BannerData> = arrayListOf()
    lateinit var homeArticleAdapter: HomeArticleAdapter
    var pageIndex: Int = 0
    var isPageLoadOver: Boolean = false
    var list: MutableList<CommonArticleItem> = arrayListOf()
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        enableBaseRefresh(false)
        enableBaseLoadMore(false)
        mBinding.ivSearch.setOnClickListener {
            startActivity<HomeSearchActivity>(mContext)
        }
        initBanner()
        initRefresh()
        initRecyclerView()
//        homeViewModel.getBannerData()
//        homeViewModel.getArticle(pageIndex)
    }

    private fun initBanner() {
        mBinding.banner.addBannerLifecycleObserver(this)
        mBinding.banner
            .setAdapter(MyBannerAdapter(bannerList))
            .setIndicator(CircleIndicator(mContext))
            .addPageTransformer(AlphaPageTransformer())
            .addPageTransformer(ScaleInTransformer())
            .setBannerRound(10f)
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(mContext)
        mBinding.recyclerView.isNestedScrollingEnabled = false
        homeArticleAdapter = HomeArticleAdapter()
        mBinding.recyclerView.adapter = homeArticleAdapter
        homeArticleAdapter.setItemClick { tag, position ->
            val item = homeArticleAdapter.getItemData(position)
            if (item != null) {
                if (tag == 1) {
                    //条目点击
                    startActivityForParams<WebActivity>(mContext) {
                        putExtra("web_url", item.link)
                        putExtra("web_title", item.title)
                    }
                } else {
                    //收藏
                    item.collect = !item.collect
                    homeArticleAdapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun initRefresh() {
        mBinding.refreshLayout.setOnRefreshListener {
            pageIndex = 0
            homeViewModel.getArticle(pageIndex)
        }
        mBinding.refreshLayout.setOnLoadMoreListener {
            if (isPageLoadOver) {
                "暂无更所数据".showToast()
                return@setOnLoadMoreListener
            }
            homeViewModel.getArticle(pageIndex)
        }
    }


    override fun onStart() {
        super.onStart()
        mBinding.banner.start()
    }

    override fun onStop() {
        super.onStop()
        mBinding.banner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.banner.destroy()
    }

    private fun finishRefresh() {
        if (mBinding.refreshLayout.isRefreshing) {
            mBinding.refreshLayout.finishRefresh()
        }
        if (mBinding.refreshLayout.isLoading) {
            mBinding.refreshLayout.finishLoadMore()
        }
    }


    override fun observe() {
        homeViewModel.bannerList.observe(this, object : BaseStateObserver<List<BannerData>>() {
            override fun getRespDataSuccess(it: List<BannerData>) {
                bannerList.clear()
                bannerList.addAll(it)
                initBanner()
            }
        })

        homeViewModel.article.observe(
            this,
            object : BaseStateObserver<CommonPageBean<CommonArticleItem>>() {
                override fun getRespDataSuccess(it: CommonPageBean<CommonArticleItem>) {
                    finishRefresh()
                    isPageLoadOver = it.over
                    if (pageIndex == 0) {
                        if (it.datas.isEmpty()) {
                            showEmptyState()
                        }
                        list.clear()
                        homeArticleAdapter.setData(null)
                    }
                    list.addAll(it.datas)
                    showContentState()
                    pageIndex++
                    homeArticleAdapter.setData(list)
                }

                override fun getRespDataEnd() {
                    finishRefresh()
                }

            })
    }
}