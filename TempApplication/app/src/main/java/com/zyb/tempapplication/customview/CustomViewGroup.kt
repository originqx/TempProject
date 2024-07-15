package com.zyb.tempapplication.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class CustomViewGroup(context: Context, attributeSet: AttributeSet) :
    ViewGroup(context, attributeSet) {


    init {
        setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var maxWidth = 0
        var totalHeight = 0

        // 测量子视图的大小并设置布局规则
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            maxWidth = Math.max(maxWidth, child.measuredWidth)
            totalHeight += child.measuredHeight
        }

        setMeasuredDimension(
            resolveSize(maxWidth, widthMeasureSpec),
            resolveSize(totalHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val childCount = childCount
        val parentLeft = paddingLeft
        var parentTop = paddingTop
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWith = child.measuredWidth
            val childheight = child.measuredHeight
            val childLeft = parentLeft
            val childTop = parentTop
            child.layout(childLeft, childTop, childLeft + childWith, childTop + childheight)
            parentTop += childheight
        }
    }

}