package com.github.adriankuta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.databinding.ItemLevel1Binding
import com.github.adriankuta.databinding.ItemLevel2Binding
import com.github.adriankuta.databinding.ItemLevel3Binding
import com.github.adriankuta.expandable_recyclerview.ExpandableRecyclerViewAdapter
import com.github.adriankuta.expandable_recyclerview.ExpandableTreeNode
import com.github.adriankuta.expandable_recyclerview.expandableTree

class ExpandableAdapter :
    ExpandableRecyclerViewAdapter<Region, ExpandableAdapter.ExpandableViewHolder>() {

    private var tree: ExpandableTreeNode<Region>? = null

    fun setTree(tree: ExpandableTreeNode<Region>) {
        this.tree = tree
        notifyDataSetChanged()
    }

    override fun getTreeNodes(): ExpandableTreeNode<Region> = tree ?: expandableTree(Region(""))

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
        treeNode: ExpandableTreeNode<Region>,
        nestLevel: Int
    ) {
        holder.bind(treeNode) {
            toggleGroup(it)
        }
    }

    sealed class ExpandableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class Level1(private val binding: ItemLevel1Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<Region>,
                onClickListener: ((ExpandableTreeNode<Region>) -> Unit)?
            ) {
                binding.apply {
                    root.setOnClickListener { onClickListener?.invoke(node) }
                    textView.text = node.value.name
                    expandIcon.isGone = node.children.isEmpty()
                    expandIcon.setImageResource(
                        if (node.expanded)
                            R.drawable.ic_expand_less_black_24dp
                        else
                            R.drawable.ic_expand_more_black_24dp
                    )
                }
            }
        }

        class Level2(private val binding: ItemLevel2Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<Region>,
                onClickListener: ((ExpandableTreeNode<Region>) -> Unit)?
            ) {
                binding.apply {
                    root.setOnClickListener { onClickListener?.invoke(node) }
                    textView.text = node.value.name
                    expandIcon.isGone = node.children.isEmpty()
                    expandIcon.setImageResource(
                        if (node.expanded)
                            R.drawable.ic_expand_less_black_24dp
                        else
                            R.drawable.ic_expand_more_black_24dp
                    )
                }
            }
        }

        class Level3(private val binding: ItemLevel3Binding) : ExpandableViewHolder(binding.root) {
            override fun bind(
                node: ExpandableTreeNode<Region>,
                onClickListener: ((ExpandableTreeNode<Region>) -> Unit)?
            ) {
                binding.apply {
                    root.setOnClickListener { onClickListener?.invoke(node) }
                    textView.text = node.value.name
                    expandIcon.isGone = node.children.isEmpty()
                    expandIcon.setImageResource(
                        if (node.expanded)
                            R.drawable.ic_expand_less_black_24dp
                        else
                            R.drawable.ic_expand_more_black_24dp
                    )
                }
            }
        }


        abstract fun bind(
            node: ExpandableTreeNode<Region>,
            onClickListener: ((ExpandableTreeNode<Region>) -> Unit)? = null
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