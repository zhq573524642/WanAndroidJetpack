package com.zhq.wanandroidjetpack.ui.note

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhq.commonlib.base.BaseActivity
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ActivityMyLearnNoteBinding
import com.zhq.wanandroidjetpack.ui.note.tablayout.TabLayoutActivity

class MyLearnNoteActivity : BaseActivity<ActivityMyLearnNoteBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_my_learn_note
    }

    override fun initView() {

        mBinding.btnTabLayout.setOnClickListener {
            startActivity<TabLayoutActivity>(mContext)
        }
    }

}