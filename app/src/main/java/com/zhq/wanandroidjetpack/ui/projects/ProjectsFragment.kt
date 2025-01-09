package com.zhq.wanandroidjetpack.ui.projects

import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.commonlib.net.BaseStateObserver
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.FragmentProjectsBinding
import com.zhq.wanandroidjetpack.ui.note.MyLearnNoteActivity
import com.zhq.wanandroidjetpack.ui.projects.adapter.ProjectFragmentPageAdapter
import com.zhq.wanandroidjetpack.ui.projects.bean.ProjectType
import com.zhq.wanandroidjetpack.ui.projects.detail.ProjectDetailFragment
import com.zhq.wanandroidjetpack.ui.projects.viewmodel.ProjectsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/2 17:39
 * Description
 */
class ProjectsFragment : BaseVMFragment<FragmentProjectsBinding>() {

    private val mViewModel: ProjectsViewModel by viewModel()
    override fun getLayoutId(): Int {
        return R.layout.fragment_projects
    }

    override fun initView() {
        mBinding.btnMyNote.setOnClickListener {
            startActivity<MyLearnNoteActivity>(mContext)
        }
//        mViewModel.getProjectsType()
    }

    private fun initTabLayout(list: List<ProjectType>) {
        for ((index, item) in list.withIndex()) {
            val newTab = mBinding.tabLayout.newTab()
            newTab.text = item.name
            newTab.id = index
            mViewModel.fragmentList.add(index, ProjectDetailFragment.newInstance(item.id))
            mBinding.tabLayout.addTab(newTab)
        }
        mBinding.viewPage.adapter = ProjectFragmentPageAdapter(
            mViewModel.fragmentList,
            childFragmentManager, lifecycle
        )
        mBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mBinding.viewPage.setCurrentItem(tab?.id ?: 0, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        mBinding.viewPage.registerOnPageChangeCallback(object :OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                mBinding.tabLayout.getTabAt(position)?.select()
            }
        })
        mBinding.viewPage.offscreenPageLimit=5
    }

    override fun observe() {
        mViewModel.projectTypeList.observe(this, object : BaseStateObserver<List<ProjectType>>() {
            override fun getRespDataSuccess(it: List<ProjectType>) {
                initTabLayout(it)
            }
        })
    }

}