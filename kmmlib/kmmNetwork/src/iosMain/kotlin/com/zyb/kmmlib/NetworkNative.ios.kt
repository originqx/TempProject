package com.zyb.kmmlib

import cocoapods.AFNetworking.*
import kotlinx.cinterop.ExperimentalForeignApi
import myCinterop.MyStringUtils


actual object NetworkNative {
    @OptIn(ExperimentalForeignApi::class)
    actual fun get(url: String) {
        val manager = AFHTTPSessionManager()

        manager.GET(
            url,
            parameters = null,
            mapOf("Authorization" to "Bearer your_token"),
            progress = null,
            success = { _, response ->
                println("Response: $response")
                println("调用IOS的.h文件中的实现: ${MyStringUtils.Companion.isStringNullOrEmpty(response.toString())}")
            },
            failure = { _, error ->
                println("Request failed: ${error?.localizedDescription}")
            }
        )
    }
}