package com.zhq.commonlib.statelayout

import com.zhq.commonlib.statelayout.source.State
import com.zhq.commonlib.statelayout.source.StateProvider


/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/14 17:19
 * Description
 */
 class CommonStateProvider : StateProvider {
    override fun getStates(): MutableList<State> {
        return mutableListOf(LoadingState(),ErrorState(),EmptyState())
    }
}