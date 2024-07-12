package com.zyb.tempapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zyb.tempapplication.retrofit.bean.User;
import com.zyb.tempapplication.retrofit.network.NetService;
import com.zyb.tempapplication.retrofit.network.RetrofitApi;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Disposable subscribe = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        LogUtils.getConfig()
//                .setBorderSwitch(true);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        LogUtils.i("sadasdasdasdas");
        RetrofitApi retrofitClient = NetService.getRetrofitClient(RetrofitApi.class);
//        subscribe = retrofitClient.getData("lisi").subscribe(new Consumer<User>() {
//            @Override
//            public void accept(User user) throws Exception {
//                Log.i("MainActivity", user.getName());
//            }
//        });

        retrofitClient.getData("lisi")
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("MainActivity", "onSubscribe---------------------");
            }

            @Override
            public void onNext(User user) {
                Log.i("MainActivity", "onNext------------------>");
                Log.i("MainActivity", user.toString());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.i("MainActivity", "onError---------------------");
            }

            @Override
            public void onComplete() {
                Log.i("MainActivity", "onComplete---------------------");
            }
        });
    }

}