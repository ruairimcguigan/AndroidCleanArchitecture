package com.example.mobileui.ui

import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

abstract class BaseAdapter<I : Item, V : RecyclerView.ViewHolder> protected constructor() : RecyclerView.Adapter<V>() {

    internal var data: MutableList<I>

    private var listener: Listener<I>? = null

    override fun getItemCount(): Int = data.size

    init {
        data = ArrayList()
    }

    fun setData(data: MutableList<I>) {
        this.data = data

        notifyDataSetChanged()
    }

    fun getData(): List<I>? {
        return data
    }

    fun setOnItemClickedListener(onItemClickedListener: Listener<I>) {
        this.listener = onItemClickedListener
    }

    protected fun onItemClicked(position: Int) {

        if (listener != null) {
            if (getItem(position) is Item) {
                listener!!.onItemClicked(data[position])
            }
        }
    }

    private fun getItem(position: Int): Any {
        return data[position]
    }

    protected fun clearData() {
        data.clear()
    }

    interface Listener<I> {
        fun onItemClicked(item: I)
    }
}
