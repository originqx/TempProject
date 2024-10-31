package com.zyb.tempapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.zyb.kmmlib.LogUtils
import com.zyb.kmmlib.NetworkHelper
import com.zyb.tempapplication.fragment.FragmentA
import com.zyb.tempapplication.fragment.FragmentB
import com.zyb.tempapplication.layout.ConstrainActivity
import com.zyb.tempapplication.layout.LineLayoutActivity
import com.zyb.tempapplication.layout.RelativeActivity
import com.zyb.tempapplication.layout.dialog.MyDialogFragment.Companion.createIns
import com.zyb.tempapplication.lifecycle.LifeCycleActivityA
import com.zyb.tempapplication.retrofit.bean.User
import com.zyb.tempapplication.retrofit.network.NetService
import com.zyb.tempapplication.retrofit.network.RetrofitApi
import com.zyb.tempapplication.utils.start
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    var network: Button? = null
    var come_to_fragment_a: Button? = null
    var come_to_fragment_b: Button? = null
    var come_to_activity_relative: Button? = null
    var come_to_activity_constain: Button? = null
    var come_to_activity_linear: Button? = null
    var show_dialog: Button? = null
    var liftCycleBtm: Button? = null
    var simpleView: View? = null
    var customViewGroup: ViewGroup? = null

    private val TAG = "MainActivity"


    var retrofitClient: RetrofitApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        initView()
        initCommonService()
    }

    private fun initCommonService() {
        retrofitClient = NetService.getRetrofitClient(RetrofitApi::class.java)
    }

    private fun initView() {
        come_to_fragment_a = findViewById(R.id.come_to_a_fargment)
        come_to_fragment_b = findViewById(R.id.come_to_b_fargment)
        network = findViewById(R.id.network)
        come_to_activity_relative = findViewById(R.id.come_to_activity_relative)
        come_to_activity_constain = findViewById(R.id.come_to_activity_constain)
        come_to_activity_linear = findViewById(R.id.come_to_activity_linear)
        show_dialog = findViewById(R.id.show_dialog)
        liftCycleBtm = findViewById(R.id.liftCycle)
        customViewGroup = findViewById(R.id.customViewGroup)
        val textView = TextView(this)
        textView.text = "文字"

        customViewGroup?.addView(textView)


        simpleView = findViewById(R.id.simpleView)
        simpleView?.setOnClickListener(View.OnClickListener { view: View? ->
            Log.i(TAG, "simpleView click")
        })

        come_to_fragment_a?.setOnClickListener(View.OnClickListener { view: View? ->
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            val instance = FragmentA.createInstance()
            //            fragmentTransaction.replace(R.id.fragment_container_view, instance);
            fragmentTransaction.add(R.id.fragment_container_view, instance)
            fragmentTransaction.commit()
        })

        come_to_fragment_b?.setOnClickListener(View.OnClickListener { view: View? ->
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment_container_view,
                FragmentB.createInstance()
            )
            fragmentTransaction.commit()
        })

        network?.setOnClickListener((View.OnClickListener { view: View? ->
            retrofitClient!!.getData("lisi")
                .subscribeOn(Schedulers.io())
                .subscribe(object :
                    Observer<User> {
                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                        Log.i("MainActivity", "onSubscribe---------------------")
                    }

                    override fun onNext(user: User) {
                        Log.i("MainActivity", "onNext------------------>")
                        Log.i("MainActivity", user.toString())
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.i("MainActivity", "onError---------------------")
                    }

                    override fun onComplete() {
                        Log.i("MainActivity", "onComplete---------------------")
                    }
                })
        }))

        come_to_activity_relative?.setOnClickListener(View.OnClickListener { view: View? ->
            val i = Intent(this, RelativeActivity::class.java)
            startActivity(i)
        })

        come_to_activity_constain?.setOnClickListener(View.OnClickListener { view: View? ->
            val i = Intent(this, ConstrainActivity::class.java)
            startActivity(i)
        })

        come_to_activity_linear?.setOnClickListener(View.OnClickListener { view: View? ->
            val i = Intent(this, LineLayoutActivity::class.java)
            startActivity(i)
        })


        show_dialog?.setOnClickListener(View.OnClickListener { view: View? ->
            showDialog(
                view
            )
        })

        liftCycleBtm?.setOnClickListener { view: View ->
            liftCycle(
                view
            )
        }

        (findViewById<Button>(R.id.kmmnetwork)!!).setOnClickListener { view: View ->
            kmmnetwork(
                view
            )
        }
    }

    private fun liftCycle(view: View) {
        start(LifeCycleActivityA::class.java)
    }

    private fun kmmnetwork(view: View) {
        NetworkHelper.getWithNative("/zyb/zhangshan")
//        NetworkHelper.getWithKtor("/zyb/zhangshan")
        LogUtils().log("KMmLog", "哈哈哈哈哈哈")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposable != null) {
            disposable!!.dispose()
        }
    }

    fun showDialog(view: View?) {
        val ins = createIns()
        ins.showAllowingLoss(this.supportFragmentManager, "sssssss")
    }
}