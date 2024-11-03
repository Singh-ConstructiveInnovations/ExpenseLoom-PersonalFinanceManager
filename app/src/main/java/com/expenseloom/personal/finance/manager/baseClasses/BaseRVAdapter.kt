package com.expenseloom.personal.finance.manager.baseClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRVAdapter<T, VB : ViewBinding>(
    private val bindingFactory: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseRVAdapter<T, VB>.BaseViewHolder>() {

    // List to hold data
    private var items: List<T> = emptyList()

    // ViewHolder class that binds the view binding
    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    // Creates a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = bindingFactory(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    // Returns the size of the data list
    override fun getItemCount(): Int = items.size

    // Bind data to the ViewHolder (subclasses must implement this)
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder.binding, position, items[position])
    }

    // Abstract function to be implemented by the subclasses to bind data to the views
    protected abstract fun bind(binding: VB,position: Int, item: T)

    // A method to set the data for the adapter
    fun submitList(data: List<T>) {
        items = data
        notifyDataSetChanged()
    }
}
