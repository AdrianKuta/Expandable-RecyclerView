package com.github.adriankuta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.databinding.ItemLevel1Binding
import com.github.adriankuta.databinding.ItemLevel2Binding
import com.github.adriankuta.databinding.ItemLevel3Binding
import com.github.adriankuta.expandable_recyclerview.ExpandableRecyclerViewAdapter
import com.github.adriankuta.expandable_recyclerview.ExpandableTreeNode
import com.github.adriankuta.expandable_recyclerview.expandableTree

class ExpandableAdapter :
    ExpandableRecyclerViewAdapter<String, ExpandableAdapter.ExpandableViewHolder>() {

    private var tree: ExpandableTreeNode<String>? = null

    fun setTree(tree: ExpandableTreeNode<String>) {
        this.tree = tree
        notifyDataSetChanged()
    }

    override fun getTreeNodes(): ExpandableTreeNode<String> = tree ?: expandableTree("")

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
        holder.bind(treeNode) {
            toggleGroup(it)
        }
    }

    sealed class ExpandableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class Level1(private val binding: ItemLevel1Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<String>,
                onClickListener: ((ExpandableTreeNode<String>) -> Unit)?
            ) {
                binding.node = node
                binding.root.setOnClickListener { onClickListener?.invoke(node) }
                binding.executePendingBindings()
            }
        }

        class Level2(private val binding: ItemLevel2Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<String>,
                onClickListener: ((ExpandableTreeNode<String>) -> Unit)?
            ) {
                binding.node = node
                binding.root.setOnClickListener { onClickListener?.invoke(node) }
                binding.executePendingBindings()
            }
        }

        class Level3(private val binding: ItemLevel3Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<String>,
                onClickListener: ((ExpandableTreeNode<String>) -> Unit)?
            ) {
                binding.node = node
                binding.root.setOnClickListener { onClickListener?.invoke(node) }
                binding.executePendingBindings()
            }
        }


        abstract fun bind(
            node: ExpandableTreeNode<String>,
            onClickListener: ((ExpandableTreeNode<String>) -> Unit)? = null
        )

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