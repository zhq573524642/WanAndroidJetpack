package com.zhq.commonlib.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhq.commonlib.R
import com.zhq.commonlib.databinding.LayoutBaseRefreshViewBinding
import com.zhq.commonlib.statelayout.EmptyState
import com.zhq.commonlib.statelayout.ErrorState
import com.zhq.commonlib.statelayout.LoadingState
import com.zhq.commonlib.statelayout.source.StateLayout
import com.zhq.commonlib.utils.LoadingUtil


/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 15:41
 * Description
 */
abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    val TAG: String = this.javaClass.simpleName
    lateinit var mContext: Context
    lateinit var mBaseBinding: LayoutBaseRefreshViewBinding
    lateinit var mBinding: VB
    lateinit var stateLayout: StateLayout
    lateinit var baseRefreshLayout: SmartRefreshLayout
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBaseBinding = DataBindingUtil.inflate<LayoutBaseRefreshViewBinding>(
            inflater,
            R.layout.layout_base_refresh_view,
            container,
            false
        )
        val baseView = layoutInflater.inflate(getLayoutId(), null)
        if (mBaseBinding.flContentContainer.childCount > 0) {
            mBaseBinding.flContentContainer.removeAllViews()
        }
        mBaseBinding.flContentContainer.addView(baseView)
        mBinding = DataBindingUtil.bind<VB>(baseView)!!
        stateLayout = mBaseBinding.baseStateLayout
        baseRefreshLayout = mBaseBinding.baseRefreshLayout
        return mBaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun initStateLayout(stateLayout: StateLayout) {
        this.stateLayout = stateLayout
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()


    inline fun <reified T> startActivity(context: Context) {
        val intent = Intent(context, T::class.java)
        context.startActivity(intent)
    }

    inline fun <reified T> startActivityForParams(context: Context, block: Intent.() -> Unit) {
        val intent = Intent(context, T::class.java)
        intent.block()
        context.startActivity(intent)
    }

    fun enableBaseRefresh(isEnable: Boolean = true) {
        if (::baseRefreshLayout.isInitialized) {
            baseRefreshLayout.setEnableRefresh(isEnable)
        }
    }

    fun enableBaseLoadMore(isEnable: Boolean = true) {
        if (::baseRefreshLayout.isInitialized) {
            baseRefreshLayout.setEnableLoadMore(isEnable)
        }
    }

    fun finishRefresh(delay: Int = 0) {
        if (::baseRefreshLayout.isInitialized) {
            baseRefreshLayout.finishRefresh(delay)
            baseRefreshLayout.finishLoadMore(delay)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
        dismissLoading()
    }

    fun showLoading() {
        LoadingUtil.showLoadingDialog(mContext, true)
    }

    fun dismissLoading() {
        LoadingUtil.dismissLoadingDialog()
    }

    fun showLoading(isCancel: Boolean) {
        LoadingUtil.showLoadingDialog(mContext, isCancel)
    }


    fun showLoadingState() {
        stateLayout?.showState(LoadingState::class.java)
    }

    fun showContentState() {
        stateLayout?.showContent()
    }


    fun showErrorState() {
        stateLayout?.showState(ErrorState::class.java)
    }

    fun showErrorState(error: String) {
        stateLayout?.getState(ErrorState::class.java)?.setErrorText(error)
        stateLayout?.showState(ErrorState::class.java)
    }

    fun showEmptyState() {
        stateLayout?.showState(EmptyState::class.java)
    }

    fun showEmptyState(emptyText: String) {
        stateLayout?.getState(EmptyState::class.java)?.setEmptyText(emptyText)
        stateLayout?.showState(EmptyState::class.java)
    }


}