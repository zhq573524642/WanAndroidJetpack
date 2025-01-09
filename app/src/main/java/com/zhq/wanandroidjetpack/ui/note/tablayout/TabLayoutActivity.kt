package com.zhq.wanandroidjetpack.ui.note.tablayout

import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.zhq.commonlib.base.BaseActivity
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ActivityTabLayoutBinding

class TabLayoutActivity : BaseActivity<ActivityTabLayoutBinding>() {

    val tabList1 = listOf("Android", "Kotlin", "JavaScript", "Python", "Vue", "ArkTS", "C++")
    val tabList2 = listOf("Android", "Kotlin", "ArkTS")
    var fragmentList: ArrayList<Fragment> = arrayListOf()
    var fragmentList1: ArrayList<Fragment> = arrayListOf()
    val tabIconList = mapOf(
        "首页" to R.raw.lottie_home,
        "消息" to R.raw.lottie_msg,
        "我的" to R.raw.lottie_mine
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_tab_layout
    }

    override fun initView() {
        tabLayout1()
        initViewPager()
    }

    private fun tabLayout1() {
        when (mBinding.tabLayout1.tabMode) {
            TabLayout.MODE_SCROLLABLE -> {
                mBinding.tabLayout1.tabMode = TabLayout.MODE_FIXED
            }

            TabLayout.MODE_FIXED -> {
                mBinding.tabLayout1.tabMode = TabLayout.MODE_AUTO
            }

            else -> {
                mBinding.tabLayout1.tabMode = TabLayout.MODE_SCROLLABLE
            }
        }


        for ((index, s) in tabList1.withIndex()) {
            fragmentList.add(TestFragment.newInstance(s, "#8800ff00"))
            fragmentList1.add(TestFragment.newInstance(s, "#88ff0000"))
            createTab(mBinding.tabLayout1, s, index)
            createTab(mBinding.tabLayout2, s, index)
            createTab(mBinding.tabLayout3, s, index)
            createTab(mBinding.tabLayout4, s, index)
            createTab(mBinding.tabLayout5, s, index)
            createTab(mBinding.tabLayout6, s, index)
            createTab(mBinding.tabLayout7, s, index)
            createTab(mBinding.tabLayout9, s, index)
            createTab(mBinding.tabLayout10, s, index)
            createTab(mBinding.tabLayout11, s, index)
            createTab(mBinding.tabLayout12, s, index)
            createTab(mBinding.tabLayout13, s, index)
            createTab(mBinding.tabLayout14, s, index)

            //Tab设置icon
            val newTab1 = mBinding.tabLayout8.newTab()
            newTab1.setIcon(R.drawable.ic_collect_select)
            newTab1.id = index
            mBinding.tabLayout8.addTab(newTab1)

        }

        for (i in 0 until mBinding.tabLayout10.tabCount) {
            val linearLayout = mBinding.tabLayout10.getChildAt(i) as? LinearLayout
            linearLayout?.let {
                it.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                it.dividerDrawable =
                    ContextCompat.getDrawable(mContext, R.drawable.shape_tab_divider)
                it.dividerPadding = 30
            }
        }
        //切换设置TabIndicate
        mBinding.tabLayout12.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val index = tab?.id
                if (index != null) {
                    if (index % 2 == 0) {
                        mBinding.tabLayout12.setSelectedTabIndicator(R.drawable.shape_full_tan_indicator_red)
                    } else {
                        mBinding.tabLayout12.setSelectedTabIndicator(R.drawable.shape_full_tan_indicator_green)
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        //数字和小红点
        mBinding.tabLayout13.getTabAt(0)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3//最大位数
                maxNumber = 100
                number = 100
                badgeGravity = BadgeDrawable.TOP_END//位置
            }
        }
        mBinding.tabLayout13.getTabAt(1)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.GREEN
                text = "新"
                badgeTextColor = Color.RED
                badgeGravity = BadgeDrawable.TOP_END//位置
            }
        }
        mBinding.tabLayout13.getTabAt(2)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.RED
            }
        }

        //模拟有红点
        mBinding.tabLayout14.getTabAt(3)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3//最大位数
                maxNumber = 100
                number = 1
                badgeGravity = BadgeDrawable.TOP_END//位置
            }
        }
        mBinding.tabLayout14.getTabAt(4)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3//最大位数
                maxNumber = 100
                number = 20
                badgeGravity = BadgeDrawable.TOP_END//位置
            }
        }

        mBinding.tabLayout14.viewTreeObserver.addOnScrollChangedListener {
            mBinding.ivHaveMore.visibility = if (isShowDot()) View.VISIBLE else View.INVISIBLE
        }

        //带图片的tab
        tabIconList.keys.forEach { s ->
            val newTab = mBinding.tabLayout15.newTab()
            val view = LayoutInflater.from(this).inflate(R.layout.item_icon_tab, null)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val lottieAnim = view.findViewById<LottieAnimationView>(R.id.lottie_anim)
            tvName.text = s
            lottieAnim.setAnimation(tabIconList[s]!!)
            newTab.setCustomView(view)
            if (s == "首页") {
                newTab.setSelected(mContext, R.color.app_theme_color, true)
            } else {
                newTab.setUnselected(mContext, R.color.color_999999, true)
            }
            mBinding.tabLayout15.addTab(newTab)
        }

        mBinding.tabLayout15.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setSelected(mContext, R.color.app_theme_color)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setUnselected(mContext, R.color.color_999999)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        // 初始化判断右侧小红点是否需要显示
        mBinding.tabLayout14.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                mBinding.ivHaveMore.visibility = if (isShowDot()) View.VISIBLE else View.INVISIBLE
                mBinding.tabLayout14.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

    }

    private fun createTab(tabLayout: TabLayout, s: String, id: Int) {
        val newTab1 = tabLayout.newTab()
        newTab1.text = s
        newTab1.id = id
        tabLayout.addTab(newTab1)
    }

    private fun initViewPager() {
        mBinding.viewPager.adapter =
            MyFragmentStatePagerAdapter(fragmentList, supportFragmentManager)
        mBinding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mBinding.viewPager2.currentItem = position
                setTabSelect(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        mBinding.viewPager2.adapter =
            MyFragmentStateAdapter(fragmentList1, supportFragmentManager, lifecycle)
        mBinding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mBinding.viewPager.currentItem = position
                setTabSelect(position)
            }
        })
    }

    private fun setTabSelect(position: Int) {
        mBinding.tabLayout1.getTabAt(position)?.select()
        mBinding.tabLayout2.getTabAt(position)?.select()
        mBinding.tabLayout3.getTabAt(position)?.select()
        mBinding.tabLayout4.getTabAt(position)?.select()
        mBinding.tabLayout5.getTabAt(position)?.select()
        mBinding.tabLayout6.getTabAt(position)?.select()
        mBinding.tabLayout7.getTabAt(position)?.select()
        mBinding.tabLayout8.getTabAt(position)?.select()
        mBinding.tabLayout9.getTabAt(position)?.select()
        mBinding.tabLayout10.getTabAt(position)?.select()
        mBinding.tabLayout11.getTabAt(position)?.select()
        mBinding.tabLayout12.getTabAt(position)?.select()
        mBinding.tabLayout13.getTabAt(position)?.select()
        mBinding.tabLayout14.getTabAt(position)?.select()
    }

    var lastShowIndex: Int = 0
    private fun isShowDot(): Boolean {
        var showIndex = 0
        var tipCount = 0
        for ((index, s) in tabList1.withIndex()) {
            mBinding.tabLayout14.getTabAt(index)?.let { tab ->
                val tabView = tab.view as LinearLayout
                val rect = Rect()
                val visible = tabView.getLocalVisibleRect(rect)
                // 可见范围小于80%也在计算范围之内，剩下20%宽度足够红点透出（可自定义）
                if (visible && rect.right > tab.view.width * 0.8) {
                    showIndex = index
                } else {
                    if (index > showIndex) { // 任意一个有count的tab隐藏就会显示，比如第一个在滑动过程中会隐藏，也在计算范围之内
//                    if (index > lastShowIndex) { // 只检测右侧隐藏且有count的tab 才在计算范围之内
                        tab.badge?.let { tipCount += it.number }
                    }
                }

            }
        }

        lastShowIndex = showIndex
        return tipCount > 0
    }

}