package com.zyb.kmmlib

import android.util.Log

actual class LogUtils {
    actual fun log(tag: String, msg: String) {
        Log.i(tag,msg)
    }
}