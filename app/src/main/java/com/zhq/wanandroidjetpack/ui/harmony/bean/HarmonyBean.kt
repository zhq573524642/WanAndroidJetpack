package com.zhq.wanandroidjetpack.ui.harmony.bean

import com.zhq.ktlearn.base.CommonArticleItem

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 17:03
 * Description
 */
data class HarmonyBean(
    val links: ItemBean,
    val open_sources: ItemBean,
    val tools: ItemBean
)

data class ItemBean(
    val id: Int,
    val name: String,
    val articleList: List<CommonArticleItem>
)
