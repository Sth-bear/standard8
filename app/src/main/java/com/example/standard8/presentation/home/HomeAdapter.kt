package com.example.standard8.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.standard8.R
import com.example.standard8.data.model.local.ImageDocument
import com.example.standard8.databinding.ItemRecyclerBinding
import com.squareup.picasso.Picasso

class HomeAdapter():RecyclerView.Adapter<HomeAdapter.Holder>() {
    var itemList = listOf<ImageDocument>()
    var savedList = listOf<ImageDocument>()

    interface ItemClick {
        fun onClick(view: View, item: ImageDocument)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = itemList[position]
        val saved:Boolean = savedList.contains(currentItem)
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it,currentItem)
        }
        if (saved){
            holder.binding.ivMark.setImageResource(R.drawable.icon_full)
        } else {
            holder.binding.ivMark.setImageResource(R.drawable.icon_empty)
        }
        holder.bind(currentItem)

    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageDocument) {
            Picasso.get()
                .load(item.imageUrl)
                .fit()
                .centerCrop()
                .into(binding.ivProfile)
            binding.apply {
                tvName.text = item.displaySiteName
            }
        }
    }

    fun listUpdate(item: List<ImageDocument>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    fun saveListupdate(item: List<ImageDocument>) {
        this.savedList = item
        notifyDataSetChanged()
    }

}

// 아이템클릭? -> 버튼에 할당. 테스트.
