package com.zyb.kmmlib

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

actual object NetworkNative {
    actual fun get(url: String) {
        val call = RetrofitClient.apiService.getRequest(url)

        call.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    println("Response: $responseBody")
                } else {
                    println("Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                println("Request failed: ${t.message}")
            }
        })
    }
}