package com.example.social_life.baseClasses

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRVMultiUIAdapter<T>() :
    RecyclerView.Adapter<BaseRVMultiUIAdapter.BaseViewHolder<T>>() {

    private var itemList: List<T> = emptyList()

    abstract fun getItemViewTypeAtPosition(item: T, position: Int): Int
    abstract fun createVH(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeAtPosition(itemList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return createVH(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(holder.binding, position, itemList[position])
    }

    abstract class BaseViewHolder<T>(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(binding: ViewBinding, position: Int, item: T)
    }

    // A method to set the data for the adapter
    fun submitList(data: List<T>) {
        itemList = data
        notifyDataSetChanged()
    }
}
