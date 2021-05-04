package com.example.mygallery

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mygallery.service.ImageSearchAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : Activity() {
    private val imageAdapter = ImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

        searchImage("life")
    }

    fun searchImage(query: String) {
        thread {
            val result = ImageSearchAPI.retrofitService.searchImages(query).execute()
            if (result.isSuccessful) {
                val items: List<ImageItem>? = result.body()?.items
                if (items?.size ?: 0 > 0) {
                    imageAdapter.imageList.clear()
                    imageAdapter.imageList.addAll(items ?: emptyList())

                    runOnUiThread {
                        imageAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}