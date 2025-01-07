package com.zhq.commonlib.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * @Author ZhangHuiQiang
 * @Date 2024/12/24 14:37
 * Description
 */
object GlideUtils {

    fun loadImage(context: Context, imageView: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
            .into(imageView)
    }
}