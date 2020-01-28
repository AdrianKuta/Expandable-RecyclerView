package com.github.adriankuta.expandable_recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ExpandableRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    private lateinit var treeNodes: ExpandableTreeNode<T>


    abstract override fun onCreateViewHolder(parent: ViewGroup, nestLevel: Int): VH

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val visibleNode = treeNodes.getVisibleNode(position, true)
        onBindViewHolder(holder, visibleNode, visibleNode.depth())
    }

    abstract fun onBindViewHolder(holder: VH, treeNode: ExpandableTreeNode<T>, nestLevel: Int)

    abstract fun getTreeNodes(): ExpandableTreeNode<T>

    final override fun getItemCount(): Int {
        treeNodes = getTreeNodes()
        return treeNodes.getVisibleNodeCount(true) //We don't want to show root element.
    }

    final override fun getItemViewType(position: Int): Int {
        return treeNodes.getVisibleNode(position, true).depth()
    }

    fun toggleGroup(expandableTreeNode: ExpandableTreeNode<T>) {
        expandableTreeNode.expanded = !expandableTreeNode.expanded
        notifyDataSetChanged()
    }

    fun expand(expandableTreeNode: ExpandableTreeNode<T>) {
        expandableTreeNode.expanded = true
        notifyDataSetChanged()
    }

    fun collapse(expandableTreeNode: ExpandableTreeNode<T>) {
        expandableTreeNode.expanded = false
        notifyDataSetChanged()
    }
}