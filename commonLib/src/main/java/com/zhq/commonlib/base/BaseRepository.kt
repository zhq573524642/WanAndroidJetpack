package com.zhq.commonlib.base

import android.util.Log
import com.zhq.commonlib.enum.ResponseState
import com.zhq.commonlib.net.BaseResponse
import com.zhq.commonlib.net.ResponseStateData
import com.zhq.commonlib.utils.ToastUtils.showToast
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @Author ZhangHuiQiang
 * @Date 2023/4/13 14:27
 * Description
 */
open class BaseRepository {

    suspend fun <T> handleResponse(
        block: suspend () -> BaseResponse<T>,
        liveData: ResponseStateData<T>,
        showLoading: Boolean = true
    ) {

        var result = BaseResponse<T>()
        result.responseState = ResponseState.START
        liveData.value = result

        try {

            result = block.invoke()

            Log.d("BaseRepository", result.errorCode.toString() + "/" + result.errorMsg)
            when (result.errorCode) {
                Constants.HTTP_SUCCESS -> {
                    result.responseState = ResponseState.SUCCESS
                }

                Constants.HTTP_AUTH_INVALID -> {
                    result.responseState = ResponseState.FAILED
                    "认证过期，请重新登录！".showToast()
//                    ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
                }

                else -> {
                    result.responseState = ResponseState.FAILED
                    ("code:" + result.errorCode.toString() + " / msg:" + result.errorMsg).showToast()
                }
            }

        } catch (e: Exception) {
            Log.d("BaseRepository", "dealResp: Exception$e")
            when (e) {
                is UnknownHostException,
                is HttpException,
                is ConnectException,
                -> {
                    //网络error
                    "网络错误！".showToast()
                }

                else -> {
                    "未知错误！".showToast()
                }
            }
            result.responseState = ResponseState.ERROR
        } finally {
            liveData.value = result
        }
    }
}