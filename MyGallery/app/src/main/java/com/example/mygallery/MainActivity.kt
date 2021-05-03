package com.example.mygallery

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private val imageAdapter = ImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

        for (i in 0 until 100) {
            imageAdapter.imageList.add(
                ImageItem("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.DK3glilaJHLDWStvRbGLaAHaHa%26pid%3DApi&f=1", "Image Item")
            )
        }

        imageAdapter.notifyDataSetChanged()
    }
}