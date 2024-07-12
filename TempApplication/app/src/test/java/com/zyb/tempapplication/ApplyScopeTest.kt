package com.zyb.tempapplication

import android.util.Log
import org.junit.Test

data class ApplyScopeTest(var name: String){
    fun getNameS():String{
        return name
    }
}

class TestScope(){
    private val name = "2"
    private var age = 12314

    fun getNameS():String{
        return name
    }


    private fun getNameY():String{
        return name
    }

    @Test
    fun testScope() {
        val scopeTest = ApplyScopeTest("3")

        scopeTest.apply {
            name = "12222"
            age = 7777
            println("getNameS: ${getNameS()}")
            println("getNameY: ${getNameY()}")
        }
        println("TestScope: $scopeTest")
        println("TestScope()的name: $name}")
        println("TestScope(）的age: $age")
    }
}
