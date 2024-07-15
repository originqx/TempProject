package com.zyb.tempapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zyb.tempapplication.fragment.FragmentA;
import com.zyb.tempapplication.fragment.FragmentB;
import com.zyb.tempapplication.layout.ConstrainActivity;
import com.zyb.tempapplication.layout.LineLayoutActivity;
import com.zyb.tempapplication.layout.RelativeActivity;
import com.zyb.tempapplication.retrofit.bean.User;
import com.zyb.tempapplication.retrofit.network.NetService;
import com.zyb.tempapplication.retrofit.network.RetrofitApi;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    Disposable disposable = null;
    Button network = null;
    Button come_to_fragment_a = null;
    Button come_to_fragment_b = null;
    Button come_to_activity_relative = null;
    Button come_to_activity_constain = null;
    Button come_to_activity_linear = null;
    View simpleView = null;
    ViewGroup customViewGroup = null;

    private String TAG = "MainActivity";



    RetrofitApi retrofitClient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        initView();
        initCommonService();

    }

    private void initCommonService() {
        retrofitClient = NetService.getRetrofitClient(RetrofitApi.class);
    }

    private void initView() {
        come_to_fragment_a = findViewById(R.id.come_to_a_fargment);
        come_to_fragment_b = findViewById(R.id.come_to_b_fargment);
        network = findViewById(R.id.network);
        come_to_activity_relative = findViewById(R.id.come_to_activity_relative);
        come_to_activity_constain = findViewById(R.id.come_to_activity_constain);
        come_to_activity_linear = findViewById(R.id.come_to_activity_linear);
        customViewGroup = findViewById(R.id.customViewGroup);
        TextView textView = new TextView(this);
        textView.setText("文字");

        customViewGroup.addView(textView);


        simpleView = findViewById(R.id.simpleView);
        simpleView.setOnClickListener((view)->{
            Log.i(TAG, "simpleView click");
        });

        come_to_fragment_a.setOnClickListener((view) ->{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            FragmentA instance = FragmentA.Companion.createInstance();
//            fragmentTransaction.replace(R.id.fragment_container_view, instance);
            fragmentTransaction.add(R.id.fragment_container_view, instance);
            fragmentTransaction.commit();
        });

        come_to_fragment_b.setOnClickListener((view) ->{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_view, FragmentB.Companion.createInstance());
            fragmentTransaction.commit();
        });

        network.setOnClickListener((view -> {
            retrofitClient.getData("lisi")
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
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
        }));

        come_to_activity_relative.setOnClickListener(view->{
            Intent i = new Intent(this, RelativeActivity.class);
            startActivity(i);
        });

        come_to_activity_constain.setOnClickListener(view->{
            Intent i = new Intent(this, ConstrainActivity.class);
            startActivity(i);
        });

        come_to_activity_linear.setOnClickListener(view->{
            Intent i = new Intent(this, LineLayoutActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}