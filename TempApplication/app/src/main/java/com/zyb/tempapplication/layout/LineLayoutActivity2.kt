package com.zyb.tempapplication.layout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Gallery
import android.widget.GridView
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zyb.tempapplication.R


class LineLayoutActivity2: AppCompatActivity() {
    private lateinit var listView: ListView
    private val fruits = arrayOf("Apple", "Banana", "Orange", "Mango", "Grape")
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linearlayout_activity2)
        initView()
    }

    private fun initView() {

        initListView()
        initGridView()
        initPopupWindow()
        initGallery()

        initRecycleView()
    }

    private fun initRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

// 创建布局管理器，可以是LinearLayoutManager、GridLayoutManager等
        val layoutManager =  LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

// 创建适配器，并设置给RecyclerView

        val recycleBean1 = RecycleBean(R.drawable.s1, "111")
        val recycleBean2 = RecycleBean(R.drawable.s2,"222")
        val recycleBean3 = RecycleBean(R.drawable.s3,"333")
        val data = mutableListOf(recycleBean1,recycleBean2,recycleBean3); // 准备数据集合
        val adapter = MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private fun initGallery() {
        val gallery = findViewById<Gallery>(R.id.gallery)
        var on = R.mipmap.on
        var offset = R.mipmap.off
        var mutableListOf = mutableListOf(on, offset)
        val adapter: GalleryAdapter = GalleryAdapter(this, mutableListOf ) // 自定义适配器
        gallery.adapter = adapter

        gallery.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                // 处理选中项的操作
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 处理没有选中项的操作
            }
        }
    }

    private fun initPopupWindow() {
        button = findViewById(R.id.button)
        button.setOnClickListener{v ->

            val popupWindow = PopupWindow()
            val contentView: View = (this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.popup_layout, null)
            popupWindow.contentView = contentView

            popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT); // 设置宽度
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT); // 设置高度
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)); // 设置背景为透明
            popupWindow.setOutsideTouchable(true); // 点击外部区域使PopupWindow消失

//        val anchorView = findViewById<View>(R.id.anchor_view)
            popupWindow.showAsDropDown(v)

            popupWindow.setOnDismissListener {
                // 处理PopupWindow关闭时的操作
            }
        }

    }

    private fun initGridView() {
        val dataList: MutableList<String> = ArrayList() // 数据列表


// 添加数据
        dataList.add("Item 1")
        dataList.add("Item 2")
        dataList.add("Item 3")


        // 获取 GridView
        val gridView = findViewById<GridView>(R.id.gridView)


// 创建适配器
        val adapter = MyGridAdapter(this, dataList)


// 设置适配器
        gridView.adapter = adapter


// 设置项点击事件监听器
        gridView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                // 处理项点击事件
                Toast.makeText(this, "选中的是： ${(view as TextView).text}个", Toast.LENGTH_SHORT).show()

            }
    }


    private fun initListView() {
        listView = findViewById(R.id.listview)

        val adapter = ArrayAdapter<String>(this, R.layout.list_item, fruits);
        listView.adapter = adapter

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                // 处理列表项的点击事件
                Toast.makeText(this, "选中的是： ${(view as TextView).text}个", Toast.LENGTH_SHORT).show()
            }
    }


}