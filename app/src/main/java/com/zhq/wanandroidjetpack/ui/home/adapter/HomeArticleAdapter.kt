package com.zhq.wanandroidjetpack.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhq.ktlearn.base.CommonArticleItem
import com.zhq.ktlearn.base.article
import com.zhq.wanandroidjetpack.R
import com.zhq.wanandroidjetpack.databinding.ItemHomeArticleBinding

class HomeArticleAdapter() :
    RecyclerView.Adapter<HomeArticleAdapter.MyViewHolder>() {

    private var diff: AsyncListDiffer<article>
    private var mBlock: ((tag: Int, position: Int) -> Unit)? = null

    init {
        diff = AsyncListDiffer(this, MyCallback())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeArticleAdapter.MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeArticleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_article,
            parent,
            false
        )
        val holder = MyViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: HomeArticleAdapter.MyViewHolder, position: Int) {
        val data = diff.currentList[position]
        holder.binding.title.text = data.title
        holder.binding.time.text = data.niceDate
        holder.binding.tvType.text = "${data.superChapterName}/${data.chapterName}"
        holder.binding.tag1.visibility = if (data.fresh) View.VISIBLE else View.GONE
        holder.binding.tag2.visibility = if (data.superChapterId == 408) View.VISIBLE else View.GONE
        holder.binding.tvAuthor.text =
            if (data.author.isEmpty()) "分享者：${data.shareUser}" else "作者：${data.author}"

        if (data.collect) {
            holder.binding.ivCollect.setImageResource(R.drawable.ic_collect_select)
        } else {
            holder.binding.ivCollect.setImageResource(R.drawable.ic_collect_unselect)
        }
        holder.itemView.setOnClickListener {
            mBlock?.invoke(1, position)
        }
        holder.binding.ivCollect.setOnClickListener {
            mBlock?.invoke(2, position)
        }
    }

    override fun getItemCount(): Int {
        return if (diff.currentList.size == 0) 0 else diff.currentList.size
    }

    fun setData(list: List<CommonArticleItem>?) {
        //AsyncListDiffer需要一个新数据，不然添加无效
        diff.submitList(if (list != null) ArrayList(list) else null)
    }

    fun getItemData(position: Int): CommonArticleItem? {
        return diff.currentList[position] ?: null
    }

    fun setItemClick(block: (tag: Int, position: Int) -> Unit) {
        this.mBlock = block
    }

    inner class MyViewHolder(val binding: ItemHomeArticleBinding) :
        RecyclerView.ViewHolder(binding.root)


    inner class MyCallback : DiffUtil.ItemCallback<article>() {
        override fun areItemsTheSame(
            oldItem: article,
            newItem: article
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: article,
            newItem: article
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

}