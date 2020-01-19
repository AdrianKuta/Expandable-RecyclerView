package com.github.adriankuta.expandable_recyclerview

import com.github.adriankuta.datastructure.tree.ChildDeclaration


@JvmSynthetic
inline fun <reified T> expandableTree(
    value: T,
    childDeclaration: ChildDeclaration<T>
): ExpandableTreeNode<T> {
    val treeNode = ExpandableTreeNode(value)
    treeNode.childDeclaration()
    return treeNode
}