package com.github.adriankuta.expandable_recyclerview

import com.github.adriankuta.datastructure.tree.ChildDeclaration
import com.github.adriankuta.datastructure.tree.TreeNode

class ExpandableTreeNode<T>(value: T) : TreeNode<T>(value) {

    var expanded: Boolean = true

    override fun child(value: T, childDeclaration: ChildDeclaration<T>?) : ExpandableTreeNode<T> {
        val newChild = ExpandableTreeNode(value)
        if (childDeclaration != null)
            newChild.childDeclaration()
        addChild(newChild)
        return newChild
    }

    /**
     * @param skipRootNode If `True`, then root node won't be counted.
     */
    internal fun getVisibleNodeCount(skipRootNode: Boolean): Int {
        var size = onlyVisibleItems()
            .size

        if (skipRootNode && size > 0)
            size--

        return size
    }

    /**
     * This function use Pre-order iteration to go through tree:
     * ```
     * e.g.
     *                    1
     *                  / | \
     *                 /  |   \
     *               2    3     4
     *              / \       / | \
     *             5    6    7  8  9
     *            /   / | \
     *           10  11 12 13
     *
     * Output (skipRootNode = false): 1 2 5 10 6 11 12 13 3 4 7 8 9
     * Output (skipRootNode = true): 2 5 10 6 11 12 13 3 4 7 8 9
     * ```
     * @param skipRootNode If `True` root element will be omitted, and position 0 will be for first left child.
     */
    internal fun getVisibleNode(position: Int, skipRootNode: Boolean): ExpandableTreeNode<T> {
        val nodePosition = if (skipRootNode) position + 1 else position
        return onlyVisibleItems()[nodePosition]
    }

    /**
     * @return List of nodes which parent or higher ancestor aren't collapsed.
     */
    fun onlyVisibleItems(): List<ExpandableTreeNode<T>> {
        //Visible if parent of node is expanded.
        return map { it as ExpandableTreeNode }
            .filter { allAncestorsAreExpanded(it) }
    }

    /**
     * `Ancestor` is a node reachable by repeated proceeding from child to parent.
     * @return `True` if parent, and all parent's ancestors are expanded.
     */
    private fun allAncestorsAreExpanded(node: ExpandableTreeNode<T>): Boolean {
        var ancestor = node.parent as? ExpandableTreeNode
        var ancestorsAreExpanded = isAncestorExpanded(ancestor)

        while (ancestorsAreExpanded && ancestor != null) {
            ancestor = ancestor.parent as? ExpandableTreeNode<T>
            ancestorsAreExpanded = ancestorsAreExpanded.and(isAncestorExpanded(ancestor))
        }

        return ancestorsAreExpanded
    }

    private fun isAncestorExpanded(ancestor: ExpandableTreeNode<T>?): Boolean {
        return ancestor?.expanded ?: true
    }

}