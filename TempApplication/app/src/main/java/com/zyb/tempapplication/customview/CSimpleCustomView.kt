package com.zyb.tempapplication.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class CSimpleCustomView(context:Context,attrs: AttributeSet):View(context,attrs) {
    companion object{
        val TAG = "CSimpleCustomView"
    }
    private lateinit var  mPaint: Paint ;
    init {
        mPaint = Paint()
        mPaint.color = Color.RED
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(50F, 50F, 200F, 200F, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.i(TAG,"MotionEvent.ACTION_DOWN")
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                Log.i(TAG,"MotionEvent.ACTION_MOVE")
                return true
            }
            MotionEvent.ACTION_UP ->{
                Log.i(TAG,"MotionEvent.ACTION_UP")
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}