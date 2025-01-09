package com.zhq.wanandroidjetpack.ui.note.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/8 10:51
 * Description
 */
class MyFragmentStatePagerAdapter(val list: ArrayList<Fragment>, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }
}