package com.zyb.kmmlib

import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Call

interface ApiService {
    @GET
    fun getRequest(@Url url: String): Call<Person> // 假设返回类型是字符串
}