package com.zhq.commonlib.net

import com.zhq.commonlib.enum.ResponseState

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/3 14:29
 * Description
 */
class BaseResponse<T> {
    var errorCode: Int = -1
    var errorMsg: String = ""
    var data: T? = null
    var responseState: ResponseState? = null
}