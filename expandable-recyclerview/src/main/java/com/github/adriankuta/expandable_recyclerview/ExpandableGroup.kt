package com.github.adriankuta.expandable_recyclerview

import com.github.adriankuta.datastructure.tree.TreeNode

open class ExpandableGroup<T>(value: T) : TreeNode<T>(value) {

    private var _isExpanded = false
    val isExpanded: Boolean
        get() = _isExpanded


    open fun expand() {
        _isExpanded = true
    }

    open fun collapse() {
        _isExpanded = false
    }
}