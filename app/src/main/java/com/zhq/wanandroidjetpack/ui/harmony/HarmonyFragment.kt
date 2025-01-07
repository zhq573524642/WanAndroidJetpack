package com.zhq.wanandroidjetpack.ui.harmony

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.commonlib.net.BaseStateObserver
import com.zhq.commonlib.utils.DensityUtils
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.FragmentHarmonyBinding
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean
import com.zhq.wanandroidjetpack.ui.harmony.viewmodel.HarmonyViewModel
import com.zhq.wanandroidjetpack.ui.web.WebActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HarmonyFragment : BaseVMFragment<FragmentHarmonyBinding>() {
    private val mViewModel: HarmonyViewModel by viewModel()
    lateinit var toolsAdapter: HarmonyDataAdapter
    lateinit var linksAdapter: HarmonyDataAdapter
    lateinit var openSourceAdapter: HarmonyDataAdapter
    val searchTagList = listOf("ohos", "axios", "http")

    override fun getLayoutId(): Int {
        return R.layout.fragment_harmony
    }

    override fun initView() {
        enableBaseRefresh(false)
        enableBaseLoadMore(false)
        initSearchTag()
        initRecyclerView()
        mViewModel.getHarmonyData()
    }

    private fun initRecyclerView() {
        val itemSpace: Int = DensityUtils.dp2px(mContext, 10f)
        toolsAdapter = HarmonyDataAdapter()
        mBinding.rvTools.isNestedScrollingEnabled = false
        mBinding.rvTools.layoutManager = GridLayoutManager(mContext, 2)
        mBinding.rvTools.addItemDecoration(ItemSpaceDecoration(itemSpace))
        mBinding.rvTools.setPadding(itemSpace, 0, 0, 0)
        mBinding.rvTools.adapter = toolsAdapter
        toolsAdapter.setItemClick { position: Int ->
            val item = toolsAdapter.getItemData(position)
            startActivityForParams<WebActivity>(mContext) {
                putExtra("web_url", item.link)
                putExtra("web_title", item.name)
            }
        }

        linksAdapter = HarmonyDataAdapter()
        mBinding.rvLinks.isNestedScrollingEnabled = false
        mBinding.rvLinks.layoutManager = GridLayoutManager(mContext, 2)
        mBinding.rvLinks.addItemDecoration(ItemSpaceDecoration(itemSpace))
        mBinding.rvLinks.setPadding(itemSpace, 0, 0, 0)
        mBinding.rvLinks.adapter = linksAdapter
        linksAdapter.setItemClick { position: Int ->
            val item = toolsAdapter.getItemData(position)
            startActivityForParams<WebActivity>(mContext) {
                putExtra("web_url", item.link)
                putExtra("web_title", item.name)
            }
        }

        openSourceAdapter = HarmonyDataAdapter()
        mBinding.rvOpen.isNestedScrollingEnabled = false
        mBinding.rvOpen.layoutManager = GridLayoutManager(mContext, 2)
        mBinding.rvOpen.addItemDecoration(ItemSpaceDecoration(itemSpace))
        mBinding.rvOpen.setPadding(itemSpace, 0, 0, 0)
        mBinding.rvOpen.adapter = openSourceAdapter
        openSourceAdapter.setItemClick { position: Int ->
            val item = toolsAdapter.getItemData(position)
            startActivityForParams<WebActivity>(mContext) {
                putExtra("web_url", item.link)
                putExtra("web_title", item.name)
            }
        }
    }

    private fun initSearchTag() {
        searchTagList.forEach { s ->
            val view = layoutInflater.inflate(R.layout.item_harmony_search_tag, null)
            val textView = view.findViewById<TextView>(R.id.tv_tag)
            textView.text = s
            mBinding.llSearchTag.addView(view)
            textView.setOnClickListener {
                startActivityForParams<WebActivity>(mContext){
                    putExtra("web_title",s)
                    putExtra("web_url","https://ohpm.openharmony.cn/#/cn/result?q=${s}")
                }
            }
        }
    }

    inner class ItemSpaceDecoration(val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.layoutManager is GridLayoutManager) {
                outRect.bottom = space
                outRect.right = space
            }
        }
    }

    private fun setHarmonyData(bean: HarmonyBean) {
        mBinding.tvTitleTools.text = bean.tools.name
        mBinding.tvTitleLinks.text = bean.links.name
        mBinding.tvTitleOpen.text = bean.open_sources.name
        toolsAdapter.setData(bean.tools.articleList)
        linksAdapter.setData(bean.links.articleList)
        openSourceAdapter.setData(bean.open_sources.articleList)
    }

    override fun observe() {
        mViewModel.data.observe(this, object : BaseStateObserver<HarmonyBean>() {
            override fun getRespDataSuccess(it: HarmonyBean) {
                setHarmonyData(it)
            }
        })
    }

}