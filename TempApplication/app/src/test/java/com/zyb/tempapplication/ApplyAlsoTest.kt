package com.zyb.tempapplication

import org.junit.Test

class ApplyAlsoTest {

    private val list = mutableListOf(1, 2, 3)

    @Test
    fun also(){
        list.also {
            it.add(4)
        }.forEach{ss ->
            println(ss.takeIf { it % 2 == 0 })
        }
    }


    inner class ss{
        var ss = list
    }


}