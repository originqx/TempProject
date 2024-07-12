package com.zyb.tempapplication.retrofit.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetService {
    public static  <T> T getRetrofitClient(final Class<T> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.241.38.19:8080")
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //获取API
        return retrofit.create(service);
    }
}
