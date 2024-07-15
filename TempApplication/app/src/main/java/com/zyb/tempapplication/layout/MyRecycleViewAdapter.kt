package com.zyb.tempapplication.layout

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zyb.tempapplication.R
import io.reactivex.annotations.NonNull


class MyAdapter(private val data: List<RecycleBean>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        // 创建ViewHolder，并关联对应的布局文件
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        // 绑定数据到ViewHolder的视图上
        val item = data[position]
        holder.textView.text = item.text
        holder.imageView.setImageResource(item.url)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById<TextView>(R.id.text)
        var imageView: ImageView = itemView.findViewById(R.id.img)
    }
}