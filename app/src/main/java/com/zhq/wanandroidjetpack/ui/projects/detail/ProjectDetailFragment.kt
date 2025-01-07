package com.zhq.wanandroidjetpack.ui.projects.detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.commonlib.net.BaseStateObserver
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.commonlib.utils.ToastUtils.showToast
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.CommonPageBean
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.FragmentProjectDetailBinding
import com.zhq.wanandroidjetpack.ui.projects.adapter.ProjectsListAdapter
import com.zhq.wanandroidjetpack.ui.projects.viewmodel.ProjectDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val PARAM_PROJECT_ID = "project_id"

class ProjectDetailFragment : BaseVMFragment<FragmentProjectDetailBinding>() {
    private val mViewModel: ProjectDetailViewModel by viewModel()
    private var projectId: Int = 0
    private var isLoadOver: Boolean = false
    lateinit var mAdapter: ProjectsListAdapter
    var pageIndex: Int = 1
    override fun getLayoutId(): Int {
        return R.layout.fragment_project_detail
    }

    companion object {
        @JvmStatic
        fun newInstance(projectTypeId: Int) =
            ProjectDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_PROJECT_ID, projectTypeId)
                }
            }
    }


    override fun initView() {
        arguments?.let {
            projectId = it.getInt(PARAM_PROJECT_ID)
        }
        enableBaseRefresh(true)
        enableBaseLoadMore(true)
        initRefresh()
        initRecyclerView()
        mViewModel.getProjectList(pageIndex, projectId)
    }

    private fun initRefresh() {
        mBaseBinding.baseRefreshLayout.setOnRefreshListener {
            pageIndex = 0
            mViewModel.getProjectList(pageIndex, projectId)
        }
        mBaseBinding.baseRefreshLayout.setOnLoadMoreListener {
           if (isLoadOver){
               "暂无更多数据".showToast()
               return@setOnLoadMoreListener
           }
            mViewModel.getProjectList(pageIndex,projectId)
        }
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(mContext)
        mAdapter = ProjectsListAdapter(mContext)
        mBinding.recyclerView.adapter = mAdapter
        mAdapter.setItemClick { position: Int ->

        }
    }



    override fun observe() {
        mViewModel.projectList.observe(this,
            object : BaseStateObserver<CommonPageBean<CommonArticleItem>>() {
                override fun getRespDataSuccess(it: CommonPageBean<CommonArticleItem>) {
                    finishRefresh()
                    isLoadOver = it.over
                    if (it.total > 0) {
                        showContentState()
                    } else {
                        showEmptyState()
                    }
                    if (it.curPage == 1) {
                        pageIndex = 1
                        mAdapter.clearData()
                    }
                    pageIndex++
                    mAdapter.setData(it.datas)
                }
            })
    }

}