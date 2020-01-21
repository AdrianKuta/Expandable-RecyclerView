package com.github.adriankuta.expandable_recyclerview

import org.junit.Assert
import org.junit.Test

class ExpandableTreeNodeTest {

    @Test
    fun getVisibleNode() {
        //given
        val root = expandableTree("Root") {
            child("Level 1") {
                child("Level 2") {
                    child("Level 3") {
                        child("Level 4")
                    }
                }
            }
        }
        root.expanded = false

        Assert.assertEquals("Root", root.onlyVisibleItems().first().value)
    }
}