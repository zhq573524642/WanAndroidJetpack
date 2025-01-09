package com.zhq.wanandroidjetpack.ui.note.tablayout

import android.content.Context
import android.graphics.ColorFilter
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.google.android.material.tabs.TabLayout
import com.zhq.wanandroidjetpack.R

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/9 14:31
 * Description
 */
fun TabLayout.Tab.setSelected(context: Context, @ColorRes id: Int, isInit: Boolean = false) {
    this.customView?.let {
        val textView = it.findViewById<TextView>(R.id.tv_name)
        val selectedColor = ContextCompat.getColor(context, id)
        textView.setTextColor(selectedColor)

        val imageView = it.findViewById<LottieAnimationView>(R.id.lottie_anim)
        if (!isInit) {
            if (!imageView.isAnimating) {
                imageView.playAnimation()
            }
        }
        setSelectedLottieColor(context, imageView, id)
    }
}

fun TabLayout.Tab.setUnselected(context: Context, @ColorRes id: Int, isInit: Boolean = false) {
    this.customView?.let {
        val textView = it.findViewById<TextView>(R.id.tv_name)
        val unselectedColor = ContextCompat.getColor(context, id)
        textView.setTextColor(unselectedColor)

        val imageView = it.findViewById<LottieAnimationView>(R.id.lottie_anim)
        if (!isInit) {
            if (imageView.isAnimating) {
                imageView.cancelAnimation()
                imageView.progress = 0f // 还原初始状态
            }
        }
        setUnSelectedLottieColor(context, imageView, id)
    }
}

private fun setSelectedLottieColor(
    context: Context,
    imageView: LottieAnimationView?,
    @ColorRes selectedColorId: Int,
) {
    imageView?.let {
        val csl = AppCompatResources.getColorStateList(context, selectedColorId)
        val filter = SimpleColorFilter(csl.defaultColor)
        val keyPath = KeyPath("**")
        val callback = LottieValueCallback<ColorFilter>(filter)
        it.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
    }
}

private fun setUnSelectedLottieColor(
    context: Context, imageView: LottieAnimationView?,
    @ColorRes unSelectedColorId: Int
) {
    imageView?.let {
        val csl = AppCompatResources.getColorStateList(context, unSelectedColorId)
        val filter = SimpleColorFilter(csl.defaultColor)
        val keyPath = KeyPath("**")
        val callback = LottieValueCallback<ColorFilter>(filter)
        it.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
    }
}
