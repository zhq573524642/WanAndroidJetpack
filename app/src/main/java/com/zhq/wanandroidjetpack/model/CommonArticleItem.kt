package com.zhq.ktlearn.base

typealias article = CommonArticleItem

class CommonArticleItem {
    val author: String = ""
    val shareUser: String = ""
    val superChapterName: String = "--"
    val superChapterId: Int = 0
    val chapterName: String = "--"
    val link: String = ""
    var collect: Boolean = false
    val id: String = ""
    val title: String = ""
    val name: String = ""
    val desc: String = ""
    val niceDate: String = ""
    val niceShareDate: String = ""
    val zan: Int = 0
    val fresh: Boolean = false
    val tags: List<CommonTagsBean> = ArrayList()
    val envelopePic: String = ""
    val projectLink: String = ""
    val userId: Int = 0
    val originId: String = ""
}