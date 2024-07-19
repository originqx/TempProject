package com.zyb.tempapplication.utils

import android.app.Activity
import android.content.Intent

class ActivityUtils {

}

fun Activity.start(clz: Class<*>){
    val intent = Intent(this, clz)
    startActivity(intent)
}