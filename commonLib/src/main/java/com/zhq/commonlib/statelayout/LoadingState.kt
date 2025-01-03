package com.zhq.commonlib.statelayout

import android.view.View

import com.zhq.commonlib.R
import com.zhq.commonlib.statelayout.source.State
import com.zhq.commonlib.statelayout.source.StateLayout

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/14 17:26
 * Description
 */
class LoadingState : State {
    override fun getLayoutId(): Int {
        return R.layout.layout_state_loading
    }

    override fun onFinishInflate(stateLayout: StateLayout, stateView: View) {
        super.onFinishInflate(stateLayout, stateView)
    }
}