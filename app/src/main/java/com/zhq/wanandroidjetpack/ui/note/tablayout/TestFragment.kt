package com.zhq.wanandroidjetpack.ui.note.tablayout

import android.graphics.Color
import android.os.Bundle
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.FragmentTestBinding


/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/8 10:43
 * Description
 */
class TestFragment : BaseVMFragment<FragmentTestBinding>() {
    private var content: String? = ""
    private var bgColor: String? = "#ffffff"

    companion object {
        @JvmStatic
        fun newInstance(content: String, bgColor: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString("content", content)
                    putString("bgColor", bgColor)
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_test
    }

    override fun initView() {
        arguments?.let {
            content = it.getString("content")
            bgColor = it.getString("bgColor")
        }
        mBinding.tvContent.text = content
        mBinding.flContainer.setBackgroundColor(Color.parseColor(bgColor))
    }

    override fun observe() {

    }

}