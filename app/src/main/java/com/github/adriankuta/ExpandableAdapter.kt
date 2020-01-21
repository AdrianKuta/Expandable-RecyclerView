package com.github.adriankuta

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.databinding.ItemLevel1Binding
import com.github.adriankuta.databinding.ItemLevel2Binding
import com.github.adriankuta.databinding.ItemLevel3Binding
import com.github.adriankuta.expandable_recyclerview.ExpandableTreeNode
import com.github.adriankuta.expandable_recyclerview.MultilevelRecyclerViewAdapter
import com.github.adriankuta.expandable_recyclerview.expandableTree

class ExpandableAdapter :
    MultilevelRecyclerViewAdapter<String, ExpandableAdapter.ExpandableViewHolder>() {

    private var tree: ExpandableTreeNode<String>? = null

    fun setTree(tree: ExpandableTreeNode<String>) {
        this.tree = tree
        notifyDataSetChanged()
    }

    override fun getTreeNodes(): ExpandableTreeNode<String> = tree ?: expandableTree("") {}

    override fun onCreateViewHolder(parent: ViewGroup, nestLevel: Int): ExpandableViewHolder {
        return when (nestLevel) {
            1 -> ExpandableViewHolder.Level1(parent.inflateLevel1())
            2 -> ExpandableViewHolder.Level2(parent.inflateLevel2())
            3 -> ExpandableViewHolder.Level3(parent.inflateLevel3())
            else -> throw IllegalArgumentException("Not implemented ViewHolder for nest level: $nestLevel")
        }
    }

    override fun onBindViewHolder(
        holder: ExpandableViewHolder,
        treeNode: ExpandableTreeNode<String>,
        nestLevel: Int
    ) {
        holder.bind(treeNode, nestLevel)
    }

    sealed class ExpandableViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        class Level1(private val binding: ItemLevel1Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(node: ExpandableTreeNode<String>, nestLevel: Int) {
                binding.node = node
            }
        }

        class Level2(private val binding: ItemLevel2Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(node: ExpandableTreeNode<String>, nestLevel: Int) {
                binding.node = node
                binding.isLastItem = isLastItem(node)
            }
        }

        class Level3(private val binding: ItemLevel3Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(node: ExpandableTreeNode<String>, nestLevel: Int) {
                binding.node = node
                binding.isLastItem = isLastItem(node)
            }
        }

        abstract fun bind(node: ExpandableTreeNode<String>, nestLevel: Int)

        fun isLastItem(node: ExpandableTreeNode<String>): Boolean {
            val parent = node.parent ?: throw IllegalArgumentException("This node hasn't parent")
            val childrenSize = parent.children.size
            Log.d("DEBUG_TAG", node.value)
            return parent.children[childrenSize - 1] == node
        }
    }

    private fun ViewGroup.inflateLevel1(): ItemLevel1Binding {
        return ItemLevel1Binding.inflate(LayoutInflater.from(context), this, false)
    }

    private fun ViewGroup.inflateLevel2(): ItemLevel2Binding {
        return ItemLevel2Binding.inflate(LayoutInflater.from(context), this, false)
    }

    private fun ViewGroup.inflateLevel3(): ItemLevel3Binding {
        return ItemLevel3Binding.inflate(LayoutInflater.from(context), this, false)
    }

}