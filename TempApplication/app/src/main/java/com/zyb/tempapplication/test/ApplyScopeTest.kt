package com.zyb.tempapplication.test

import android.util.Log

data class ApplyScopeTest(var name: String){
    fun getNameS():String{
        return name
    }
}

class TestScope(){
    val name = "2"
    private var age = 12314

    fun getNameS():String{
        return name
    }
    private fun getNameY():String{
        return name
    }

    init {
        val scopeTest = ApplyScopeTest("3")

        scopeTest.apply {
            name = "12222"
            age = 7777
            Log.i("getNameS", getNameS())
            Log.i("getNameY", getNameY())
        }
        Log.i("TestScope", scopeTest.toString())
        Log.i("TestScope()的name", name)
        Log.i("TestScope(）的age", age.toString())
    }
}
