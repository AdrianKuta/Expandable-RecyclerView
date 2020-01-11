package com.github.adriankuta.expandable_recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class MultilevelRecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {

    abstract fun getExpandableGroups(): ExpandableGroup<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        getExpandableGroups().toList()
        return getExpandableGroups().nodeCount()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}