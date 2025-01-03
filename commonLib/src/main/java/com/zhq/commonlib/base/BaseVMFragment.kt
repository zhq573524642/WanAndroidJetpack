package com.zhq.commonlib.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/11 11:39
 * Description
 */
abstract class BaseVMFragment<T : ViewDataBinding> : BaseFragment<T>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    abstract fun observe()
}