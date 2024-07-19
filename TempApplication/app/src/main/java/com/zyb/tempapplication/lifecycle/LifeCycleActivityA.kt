package com.zyb.tempapplication.lifecycle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.zyb.tempapplication.R
import kotlin.random.Random

class LifeCycleActivityA : AppCompatActivity() {
    private var viewMode : MyViewModel? = null
    private lateinit var mTextView: TextView
    private lateinit var btm: View
    private var mFragment: LiveDataFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_life_cycle_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mTextView = findViewById(R.id.text)
        btm = findViewById(R.id.button)

        lifecycle.addObserver(MyLifeCycleObserver())



//        viewMode = MyViewModel()
        viewMode = ViewModelProvider(this)[MyViewModel::class]
        viewMode?.itemLiveData?.observe(this) { itemStr ->
            mTextView.text = itemStr
        }
        addLiveDataFragment()
    }

    fun updateValue(view: View) {
        sendData(produceData())
    }

    //随机更新一个整数
    private fun produceData(): String {
        val randomValue = (0..1000).random().toString()
        mTextView.text = "Activity中发送：$randomValue"
        return randomValue
    }

    //通过setValue发送更新
    private fun sendData(randomValue: String) {
        viewMode?.itemLiveData?.value = randomValue
    }

    //添加Fragment
    fun addFragment(view: View) {
        addLiveDataFragment()
    }

    //移除Fragment
    fun removeFragment(view: View) {
        delLiveDataFragment()
    }

    private fun addLiveDataFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (fragment != null) {
            Toast.makeText(this,"请不要重复添加",Toast.LENGTH_SHORT).show()
        }

        if (mFragment == null) {
            mFragment = LiveDataFragment.newInstance("1", "2")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view,mFragment!!)
            .commitAllowingStateLoss()
    }

    private fun delLiveDataFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (fragment == null) {
            Toast.makeText(this, "没有Fragment", Toast.LENGTH_SHORT).show()
            return
        }
        supportFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
    }


    fun pressMe(view: View) {
        viewMode?.updateText(Random.nextInt(100).toString())
    }
}