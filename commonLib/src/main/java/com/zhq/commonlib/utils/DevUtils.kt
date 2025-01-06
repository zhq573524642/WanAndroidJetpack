package com.zhq.commonlib.utils

import kotlin.math.floor

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 14:15
 * Description
 */
object DevUtils {

//    static getRandomColor() {
//        const letters: string = '0123456789ABCDEF';
//        let color: string = '#';
//        for (let i = 0; i < 6; i++) {
//            let Itemp: number = Math.floor(Math.random() * 16)
//            color += letters[Itemp];
//        }
//        console.log(color)
//        return color;
//    }

    fun getRandomColor(): String {
        val letter = "0123456789ABCDEF"
        var color: String = "#"
        for (i in 0 until 6) {
            val temp: Int = floor(Math.random() * 16).toInt()
            color += letter[temp]
        }
        return color
    }
    fun getRandomColorWithAlpha(alpha:String="FF"): String {
        val letter = "0123456789ABCDEF"
        var color: String = "#${alpha}"
        for (i in 0 until 6) {
            val temp: Int = floor(Math.random() * 16).toInt()
            color += letter[temp]
        }
        return color
    }
}