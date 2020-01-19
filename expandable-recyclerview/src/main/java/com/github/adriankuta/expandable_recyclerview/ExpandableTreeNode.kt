package com.github.adriankuta.expandable_recyclerview

import com.github.adriankuta.datastructure.tree.ChildDeclaration
import com.github.adriankuta.datastructure.tree.TreeNode

class ExpandableTreeNode<T>(value: T) : TreeNode<T>(value) {

    var expanded: Boolean = true
        set(value) {
            field = value
            children.forEach {
                (it as ExpandableTreeNode).expanded = value
            }
        }

    override fun child(value: T, childDeclaration: ChildDeclaration<T>?) {
        val newChild = ExpandableTreeNode(value)
        if (childDeclaration != null)
            newChild.childDeclaration()
        addChild(newChild)
    }

    fun getVisibleNodeCount(): Int {
        return onlyVisibleItems()
            .size
    }

    fun getVisibleNode(position: Int): ExpandableTreeNode<T> {
        return onlyVisibleItems()[position]
    }

    private fun onlyVisibleItems(): List<ExpandableTreeNode<T>> {
        //Visible if parent of node is expanded.
        return map { it as ExpandableTreeNode }
            .filter { (it.parent as? ExpandableTreeNode)?.expanded ?: true }
    }

}