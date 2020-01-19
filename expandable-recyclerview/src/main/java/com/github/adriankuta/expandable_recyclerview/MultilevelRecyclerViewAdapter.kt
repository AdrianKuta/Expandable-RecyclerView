package com.github.adriankuta.expandable_recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class MultilevelRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    private lateinit var treeNodes: ExpandableTreeNode<T>


    abstract override fun onCreateViewHolder(parent: ViewGroup, nestLevel: Int): VH

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, treeNodes.getVisibleNode(position))
    }

    abstract fun onBindViewHolder(holder: VH, treeNode: ExpandableTreeNode<T>)

    abstract fun getTreeNodes(): ExpandableTreeNode<T>

    final override fun getItemCount(): Int {
        treeNodes = getTreeNodes()
        return treeNodes.getVisibleNodeCount()
    }

    final override fun getItemViewType(position: Int): Int {

        return treeNodes.getVisibleNode(position).depth()
    }

    fun toggleGroup(expandableTreeNode: ExpandableTreeNode<T>) {
        expandableTreeNode.expanded = !expandableTreeNode.expanded
        notifyDataSetChanged()
    }
}