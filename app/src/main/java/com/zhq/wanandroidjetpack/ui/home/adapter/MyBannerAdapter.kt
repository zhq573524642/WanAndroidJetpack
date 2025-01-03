package com.zhq.wanandroidjetpack.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import com.zhq.commonlib.utils.GlideUtils
import com.zhq.wanandroidjetpack.App
import com.zhq.wanandroidjetpack.databinding.ItemHomeBannerBinding
import com.zhq.wanandroidjetpack.ui.home.bean.BannerData

class MyBannerAdapter(datas: MutableList<BannerData>?) :
    BannerAdapter<BannerData, MyBannerAdapter.BannerViewHolder>(datas) {
    inner class BannerViewHolder(val binding: ItemHomeBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val binding =
            ItemHomeBannerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        val holder = BannerViewHolder(binding)
        return holder
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: BannerData?,
        position: Int,
        size: Int
    ) {
        holder?.binding?.let {
            it.imageview.let {
                it.adjustViewBounds=true
                it.scaleType= ImageView.ScaleType.FIT_CENTER
            }
            GlideUtils.loadImage(App.mContext, it.imageview, data?.imagePath) }
    }


}