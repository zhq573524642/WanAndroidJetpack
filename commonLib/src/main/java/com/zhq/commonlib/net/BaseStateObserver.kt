package com.zhq.commonlib.net

import android.util.Log
import androidx.lifecycle.Observer
import com.zhq.commonlib.enum.ResponseState

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:26
 * Description
 */
open class BaseStateObserver<T>() :
    Observer<BaseResponse<T>> {

    override fun onChanged(t: BaseResponse<T>) {

        when (t.responseState) {
            ResponseState.START -> {
                Log.d("BaseStateObserver", "Observer: start")
                getRespDataStart()
            }

           ResponseState.SUCCESS -> {
                Log.d("BaseStateObserver", "Observer: success")
                if (t.data == null) {
                    getRespSuccess()
                } else {
                    getRespDataSuccess(t.data!!)
                }

            }

            ResponseState.FAILED -> {
                Log.d("BaseStateObserver", "Observer: failed")
                getRespDataEnd()
            }

           ResponseState.ERROR -> {
                Log.d("BaseStateObserver", "Observer: error")
                getRespDataEnd()
            }
            else->{

            }
        }
    }

    open fun getRespDataStart() {}
    open fun getRespDataSuccess(it: T) {}
    open fun getRespSuccess() {}
    open fun getRespDataEnd() {}
}