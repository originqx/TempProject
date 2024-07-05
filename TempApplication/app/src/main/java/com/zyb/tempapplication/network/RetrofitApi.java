package com.zyb.tempapplication.network;

import com.zyb.tempapplication.bean.User;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

//定义 网络API 地址
public interface RetrofitApi{
    @GET("/zyb/{userName}")
    Observable<User> getData(@Path("userName") String user);
}
