package com.zhq.commonlib.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import kotlin.math.min

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 10:21
 * Description
 */
object DensityUtils {

    /**
     * 单位转化 dp转px
     * @param dpValue
     * @return
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return Math.round(dpValue * scale)
    }

    /**
     * 描述：px转换为dp
     * @param context
     * @param pxValue
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return Math.round(pxValue / scale)
    }

    /**
     *
     * 描述：px转换为sp
     * @param context
     * @param pxValue
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return Math.round(pxValue / scale)
    }

    /**
     *
     * 描述：sp转换为px
     * @param context
     * @param spValue
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return Math.round(spValue * scale)
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param
     * （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    //    public static int sp2px(Context context, float spValue) {
    //        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    //        return (int) (spValue * fontScale + 0.5f);
    //    }
    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val res = context.resources
        return res.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度 包含状态栏的高度
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val res = context.resources
        return res.displayMetrics.heightPixels
    }

    /**
     * 获取View的高(View宽度match_parent，设置margin)
     * @param context
     * @param marginSize
     * @param ratioWidth
     * @param ratioHeight
     * @return
     */
    fun getViewHeight(context: Context, marginSize: Int, ratioWidth: Int, ratioHeight: Int): Int {
        val res = context.resources
        val screenWidth = res.displayMetrics.widthPixels
        val scale = res.displayMetrics.density
        val margin = (marginSize * scale + 0.5f).toInt()
        val imageWidth = screenWidth - margin
        val imageHeight = (imageWidth * ratioHeight) / ratioWidth
        return imageHeight
    }

    /**
     * 设置margin
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.layoutParams is MarginLayoutParams) {
            val p = v.layoutParams as MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }

    /**
     * 描述：根据分辨率获得字体大小.
     *
     * @param screenWidth the screen width
     * @param screenHeight the screen height
     * @param textSize the text size
     * @return the int
     */
    fun resizeTextSize(screenWidth: Int, screenHeight: Int, textSize: Int): Int {
        var ratio = 1f
        try {
            val ratioWidth = screenWidth.toFloat() / 480
            val ratioHeight = screenHeight.toFloat() / 800
            ratio = min(ratioWidth.toDouble(), ratioHeight.toDouble()).toFloat()
        } catch (e: Exception) {
        }
        return Math.round(textSize * ratio)
    }
}