package com.zhq.wanandroidjetpack

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.zhq.commonlib.base.BaseActivity
import com.zhq.commonlib.utils.ToastUtils.showToast
import com.zhq.wanandroidjetpack.ui.home.HomeFragment
import com.zhq.wanandroidjetpack.databinding.ActivityMainBinding
import com.zhq.wanandroidjetpack.ui.harmony.HarmonyFragment
import com.zhq.wanandroidjetpack.ui.mine.MineFragment
import com.zhq.wanandroidjetpack.ui.projects.ProjectsFragment
import com.zhq.wanandroidjetpack.ui.questions.QuestionsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAB_MAIN_0: Int = 0;
    val TAB_MAIN_1: Int = 1;
    val TAB_MAIN_2: Int = 2;
    val TAB_MAIN_3: Int = 3;
    val TAB_MAIN_4: Int = 4;
    private lateinit var currentFragment: Fragment

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initFragment(0)
        mBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_1 -> {
                    switchFragment(0)
                    return@setOnItemSelectedListener true
                }

                R.id.tab_2 -> {
                    switchFragment(1)
                    return@setOnItemSelectedListener true
                }

                R.id.tab_3 -> {
                    switchFragment(2)
                    return@setOnItemSelectedListener true
                }

                R.id.tab_4 -> {
                    switchFragment(3)
                    return@setOnItemSelectedListener true
                }

                R.id.tab_5 -> {
                    switchFragment(4)
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false
            }
        }
        val badge = mBinding.bottomNavigationView.getOrCreateBadge(R.id.tab_1)
        badge.backgroundColor = Color.RED
        badge.badgeTextColor = Color.WHITE
        badge.number = 99
    }

    private val bottomNavigationSelectedListener =
        NavigationBarView.OnItemSelectedListener()

        { item ->
            when (item.itemId) {
                R.id.tab_1 -> {
                    switchFragment(0)
                    return@OnItemSelectedListener true
                }

                R.id.tab_2 -> {
                    switchFragment(1)
                    return@OnItemSelectedListener true
                }

                R.id.tab_3 -> {
                    switchFragment(2)
                    return@OnItemSelectedListener true
                }

                R.id.tab_4 -> {
                    switchFragment(3)
                    return@OnItemSelectedListener true
                }

                R.id.tab_5 -> {
                    switchFragment(4)
                    return@OnItemSelectedListener true
                }

                else -> return@OnItemSelectedListener false
            }

        }

    private fun initFragment(index: Int) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        currentFragment = createFragment(index)
        if (!currentFragment.isAdded) {
            fragmentTransaction.add(R.id.fl_container, currentFragment, createFragmentTag(index))
        }
        fragmentTransaction.commitNowAllowingStateLoss()
    }

    private fun switchFragment(index: Int) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(0, 0)
        val tag = createFragmentTag(index)
        var targetFragment = supportFragmentManager.findFragmentByTag(tag)
        if (targetFragment == null) {
            targetFragment = createFragment(index)
            fragmentTransaction.hide(currentFragment)
                .add(R.id.fl_container, targetFragment, createFragmentTag(index))
                .commitNowAllowingStateLoss()
            currentFragment = targetFragment
        } else {
            if (targetFragment != currentFragment) {
                fragmentTransaction.hide(currentFragment).show(targetFragment)
                    .commitNowAllowingStateLoss()
                currentFragment = targetFragment
            }
        }
    }


    private fun createFragment(index: Int): Fragment {
        val fragment = when (index) {
            TAB_MAIN_0 -> HomeFragment()
            TAB_MAIN_1 -> HarmonyFragment()
            TAB_MAIN_2 -> ProjectsFragment()
            TAB_MAIN_3 -> QuestionsFragment()
            TAB_MAIN_4 -> MineFragment()
            else -> HomeFragment()
        }
        return fragment
    }

    private fun createFragmentTag(index: Int): String {
        val s = when (index) {
            TAB_MAIN_0 -> "home"
            TAB_MAIN_1 -> "harmony"
            TAB_MAIN_2 -> "projects"
            TAB_MAIN_3 -> "questions"
            TAB_MAIN_4 -> "mine"
            else -> "home"
        }
        return s
    }


    private var exitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            "再按一次退出${resources.getString(R.string.app_name)}".showToast()
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }
}