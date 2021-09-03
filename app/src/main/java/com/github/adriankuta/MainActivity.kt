package com.github.adriankuta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.databinding.ActivityMainBinding
import com.github.adriankuta.expandable_recyclerview.expandableTree

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

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

        with(binding) {
            val adapter = ExpandableAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter

            adapter.setTree(tree)
        }
    }
}
