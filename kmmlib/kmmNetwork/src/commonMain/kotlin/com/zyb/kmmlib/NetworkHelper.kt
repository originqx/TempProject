package com.zyb.kmmlib

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmStatic

object NetworkHelper {

    const val BASE_URL = "http://10.241.41.192:8080"
    @JvmStatic
    fun getWithNative(url: String) {
        NetworkNative.get(url)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @JvmStatic
    fun getWithKtor(url: String) {
        GlobalScope.launch {
            val data: Person = client.get(url).body()
            println("Received data: $data")
        }
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // 忽略未知的 JSON 字段
                isLenient = true // 宽松模式
            })
        }
        install(DefaultRequest) {
            // 设置全局的 baseURL
            url(BASE_URL)
        }
    }

    suspend fun getData(url: String): String {
        val response: HttpResponse = client.get(url)
        return response.bodyAsText()
    }
}