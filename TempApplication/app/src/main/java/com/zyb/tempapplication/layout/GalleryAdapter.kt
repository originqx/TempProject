package com.zyb.tempapplication.layout

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView


class GalleryAdapter(private val context: Context, private val images: List<Int>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView = if (convertView == null) {
            ImageView(context)
        } else {
            convertView as ImageView
        }

        imageView.setImageResource(images[position])
        return imageView
    }
}