package com.zhq.commonlib.statelayout

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.zhq.commonlib.R
import com.zhq.commonlib.statelayout.source.State
import com.zhq.commonlib.statelayout.source.StateLayout

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/14 17:26
 * Description
 */
class ErrorState : State {
    private lateinit var errorText: String
    override fun getLayoutId(): Int {
        return R.layout.layout_state_error
    }

    fun setErrorText(error: String) {
        this.errorText = error
    }

    override fun onFinishInflate(stateLayout: StateLayout, stateView: View) {
        if (!::errorText.isInitialized) {
            errorText = "网络异常"
        }
        stateView.findViewById<Button>(R.id.btn_state_error_retry).setOnClickListener {
            stateLayout.dispatchRetryClickListener(it)
        }
        stateLayout.findViewById<TextView>(R.id.tv_state_error_title).text = this.errorText
    }
}