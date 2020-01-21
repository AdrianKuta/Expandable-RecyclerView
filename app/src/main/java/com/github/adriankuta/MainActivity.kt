package com.github.adriankuta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.expandable_recyclerview.expandableTree
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tree = expandableTree("World") {
            child("North America") {
                child("USA")
            }
            child("Europe") {
                child("Poland") {
                    child("Warsaw")
                }
                child("Germany")
            }
            child("Asia") {
                child("China")
            }
        }

        val adapter = ExpandableAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter.setTree(tree)
    }
}
