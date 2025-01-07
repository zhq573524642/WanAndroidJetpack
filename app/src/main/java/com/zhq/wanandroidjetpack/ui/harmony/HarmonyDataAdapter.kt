package com.zhq.wanandroidjetpack.ui.harmony

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ItemHarmonyDataBinding
import com.zhq.wanandroidjetpack.ui.harmony.bean.HarmonyBean
import com.zhq.wanandroidjetpack.ui.harmony.bean.ItemBean

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/6 17:10
 * Description
 */
class HarmonyDataAdapter : RecyclerView.Adapter<HarmonyDataAdapter.MyViewHolder>() {
    private var list: List<CommonArticleItem> = arrayListOf()
    private var mBLOCK: ((position: Int) -> Unit)? = null

    inner class MyViewHolder(val binding: ItemHarmonyDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemHarmonyDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_harmony_data,
            parent, false
        )
        val holder = MyViewHolder(binding)
        return holder
    }

    fun setData(list: List<CommonArticleItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun getItemData(position: Int):CommonArticleItem{
        return list[position]
    }

    fun setItemClick(block: (position: Int) -> Unit) {
        this.mBLOCK = block
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) 0 else list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDesc.text = item.desc

        holder.itemView.setOnClickListener {
            mBLOCK?.invoke(position)
        }
    }


}