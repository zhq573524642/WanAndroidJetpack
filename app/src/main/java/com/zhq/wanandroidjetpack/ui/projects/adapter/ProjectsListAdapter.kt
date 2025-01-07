package com.zhq.wanandroidjetpack.ui.projects.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhq.commonlib.utils.GlideUtils
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ItemProjectDetailBinding

/**
 * @Author ZhangHuiQiang
 * @Date 2025/1/7 16:06
 * Description
 */
class ProjectsListAdapter(val context: Context) :
    RecyclerView.Adapter<ProjectsListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemProjectDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var list: ArrayList<CommonArticleItem> = arrayListOf()
    private var mBLOCK: ((position: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemProjectDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_project_detail,
            parent, false
        )
        val holder = MyViewHolder(binding)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            mBLOCK?.invoke(position)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvAuthor.text = item.author
        holder.binding.tvDesc.text = item.desc
        holder.binding.tvDate.text = item.niceDate
        holder.binding.ivCollect.setImageResource(if (item.collect) R.drawable.ic_collect_select else R.drawable.ic_collect_unselect)
        GlideUtils.loadImage(context, holder.binding.ivIcon, item.envelopePic)
    }

    fun clearData() {
        this.list.clear()
        notifyDataSetChanged()
    }

    fun setData(list: List<CommonArticleItem>) {
        val size = list.size
        this.list.addAll(list)
        notifyItemRangeChanged(size, this.list.size)
    }

    fun getItemData(position: Int): CommonArticleItem {
        return list[position]
    }

    fun setItemClick(block: (position: Int) -> Unit) {
        this.mBLOCK = block
    }

}