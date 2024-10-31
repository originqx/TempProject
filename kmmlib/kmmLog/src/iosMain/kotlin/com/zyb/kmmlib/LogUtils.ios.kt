package com.zyb.kmmlib

import platform.Foundation.NSLog

actual class LogUtils {
    actual fun log(tag: String, msg: String) {
        NSLog("[$tag] $msg")
    }
}