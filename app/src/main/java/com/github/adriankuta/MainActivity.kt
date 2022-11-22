package com.github.adriankuta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.adriankuta.databinding.ActivityMainBinding
import com.github.adriankuta.expandable_recyclerview.expandableTree

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.recyclerView.adapter = adapter
        adapter.setTree(tree)
    }
}
