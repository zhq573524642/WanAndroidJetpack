package com.zhq.wanandroidjetpack.ui.note.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/8 10:56
 * Description
 */
class MyFragmentStateAdapter(
    val list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}