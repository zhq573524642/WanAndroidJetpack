package com.zhq.wanandroidjetpack.ui.home.adapter

import android.graphics.Color
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhq.commonlib.utils.DevUtils
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ItemRandomColorBgBinding
import com.zhq.wanandroidjetpack.ui.home.bean.SearchHotWords


/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 15:05
 * Description
 */
class SearchHotWordsAdapter :
    RecyclerView.Adapter<SearchHotWordsAdapter.MyViewHolder>() {
    private var list: List<SearchHotWords> = arrayListOf()
    private var mBlock: ((position: Int) -> Unit)? = null

    inner class MyViewHolder(val binding: ItemRandomColorBgBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemRandomColorBgBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_random_color_bg,
            parent, false
        )
        val holder = MyViewHolder(binding)
        return holder
    }

    fun setData(list: List<SearchHotWords>) {
        this.list = list
    }

    fun getItem(position: Int): SearchHotWords {
        return this.list[position]
    }

    fun setItemClick(block: (position: Int) -> Unit) {
        this.mBlock = block
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) 0 else list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvTitle.text = item.name
        holder.binding.cv.setCardBackgroundColor(Color.parseColor(DevUtils.getRandomColorWithAlpha("aa")))
        holder.itemView.setOnClickListener {
            mBlock?.invoke(position)
        }
    }
}