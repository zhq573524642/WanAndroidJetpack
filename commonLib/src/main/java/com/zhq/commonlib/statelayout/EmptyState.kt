package com.zhq.commonlib.statelayout

import android.view.View
import android.widget.TextView
import com.zhq.commonlib.R
import com.zhq.commonlib.statelayout.source.State
import com.zhq.commonlib.statelayout.source.StateLayout

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/14 17:26
 * Description
 */
class EmptyState : State {
    private lateinit var emptyText: String
    override fun getLayoutId(): Int {
        return R.layout.layout_state_empty
    }

    fun setEmptyText(msg: String) {
        this.emptyText = msg
    }

    override fun onFinishInflate(stateLayout: StateLayout, stateView: View) {
        if (!::emptyText.isInitialized) {
            emptyText = "暂无数据"
        }
        stateLayout.findViewById<TextView>(R.id.tv_state_empty_title).text = this.emptyText
    }
}