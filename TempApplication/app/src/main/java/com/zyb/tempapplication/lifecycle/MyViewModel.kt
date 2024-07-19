package com.zyb.tempapplication.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel :ViewModel(){
    val itemLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun updateText(info:String){
        itemLiveData.value = info
    }

}