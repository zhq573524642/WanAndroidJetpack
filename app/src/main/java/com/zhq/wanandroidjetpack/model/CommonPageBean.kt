package com.zhq.ktlearn.base

/**
 * @Author ZhangHuiQiang
 * @Date 2024/12/25 11:39
 * Description
 */
class CommonPageBean<T> {
    val curPage: Int = 0
    val offset: Int = 0
    val over: Boolean = false
    val pageCount: Int = 0
    val size: Int = 0
    val total: Int = 0
    val datas: List<T> = emptyList()
}