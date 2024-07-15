package com.zyb.tempapplication.layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.zyb.tempapplication.R


class MyGridAdapter(private val context: Context, private val dataList: List<String>) :
    BaseAdapter() {
    // 实现必要的方法...
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.grid_item_layout, parent, false)

            holder = ViewHolder()
            holder.itemTextView = convertView.findViewById<TextView>(R.id.itemTextView)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val item = dataList[position]
        holder.itemTextView!!.text = item

        return convertView!!
    }

    private class ViewHolder {
        var itemTextView: TextView? = null
    }
}