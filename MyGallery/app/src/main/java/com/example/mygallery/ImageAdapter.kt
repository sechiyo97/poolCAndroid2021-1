package com.example.mygallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.net.URL

/**
 * Created by seheelee on 2021-04-27.
 */

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    val imageList = mutableListOf<ImageItem>()

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.image)
        val itemText: TextView = view.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]

        Glide.with(holder.itemImage.context)
            .load(Uri.parse(item.src))
            .into(holder.itemImage)

        holder.itemText.text = item.description + ", " + position.toString()
    }

    override fun getItemCount() = imageList.size
}