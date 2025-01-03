package com.zhq.commonlib.base

import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhq.commonlib.R
import com.zhq.commonlib.databinding.LayoutBaseViewBinding
import com.zhq.commonlib.statelayout.EmptyState
import com.zhq.commonlib.statelayout.ErrorState
import com.zhq.commonlib.statelayout.LoadingState
import com.zhq.commonlib.statelayout.source.StateLayout
import com.zhq.commonlib.statelayout.source.anim.AlphaTransitionAnimator
import com.zhq.commonlib.statelayout.source.anim.AlphaTranslationTransitionAnimator
import com.zhq.commonlib.statelayout.source.anim.TranslationTransitionAnimator
import com.zhq.commonlib.utils.LoadingUtil

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/2 15:49
 * Description
 */
enum class StateLayoutEnum {
    Alpha, Translation, AlphaTranslation
}

abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    val TAG: String = this.javaClass.simpleName.toString()
    lateinit var mBaseBinding: LayoutBaseViewBinding
    lateinit var mBinding: VB
    lateinit var mContext: Context
    lateinit var stateLayout: StateLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mContext = this
        mBaseBinding =
            DataBindingUtil.setContentView<LayoutBaseViewBinding>(
                this,
                 R.layout.layout_base_view
            )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val baseView = layoutInflater.inflate(getLayoutId(), null)
        mBaseBinding.flContentContainer.addView(baseView)
        stateLayout = mBaseBinding.baseStateLayout
        mBinding = DataBindingUtil.bind<VB>(baseView)!!
        initView()
    }

    // Please call init before use
    protected fun initStateLayout(stateLayout: StateLayout) {
        this.stateLayout = stateLayout
    }


    fun setGrayTheme() {
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }


    inline fun <reified T> startActivity(context: Context) {
        val intent = Intent(context, T::class.java)
        context.startActivity(intent)
    }

    inline fun <reified T> startActivityForParams(context: Context, block: Intent.() -> Unit) {
        val intent = Intent(context, T::class.java)
        intent.block()
        context.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
        mBinding.unbind()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    fun setStateLayoutAnim(anim: StateLayoutEnum) {
        when (anim.name) {
            StateLayoutEnum.Translation.name -> {
                stateLayout.transitionAnimator = TranslationTransitionAnimator()
            }

            StateLayoutEnum.AlphaTranslation.name -> {
                stateLayout.transitionAnimator = AlphaTranslationTransitionAnimator()
            }

            else -> {
                stateLayout.transitionAnimator = AlphaTransitionAnimator()//默认
            }
        }
    }

    fun showLoading() {
        LoadingUtil.showLoadingDialog(mContext, true)
    }

    fun showLoading(isCancel: Boolean) {
        LoadingUtil.showLoadingDialog(mContext, isCancel)
    }

    fun dismissLoading() {
        LoadingUtil.dismissLoadingDialog()
    }


    fun showLoadingState() {
        stateLayout.showState(LoadingState::class.java)
    }

    fun showContentState() {
        stateLayout.showContent()
    }

    fun showErrorState() {
        stateLayout.showState(ErrorState::class.java)
    }

    fun showErrorState(error: String) {
        stateLayout.getState(ErrorState::class.java)?.setErrorText(error)
        stateLayout.showState(ErrorState::class.java)
    }

    fun showEmptyState() {
        stateLayout.showState(EmptyState::class.java)
    }

    fun showEmptyState(emptyText: String) {
        stateLayout.getState(EmptyState::class.java)?.setEmptyText(emptyText)
        stateLayout.showState(EmptyState::class.java)
    }


}