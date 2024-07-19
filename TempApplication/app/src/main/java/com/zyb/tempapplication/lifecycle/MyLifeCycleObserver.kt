package com.zyb.tempapplication.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MyLifeCycleObserver : LifecycleEventObserver {
    companion object{
        const val TAG :String = "MyLifeCycleObserver"
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START-> Log.i(TAG, "connect: ${source.lifecycle.currentState}")
            Lifecycle.Event.ON_STOP -> Log.i(TAG, "disconnect: ${source.lifecycle.currentState}")
            else -> Log.i(TAG, "other: ${source.lifecycle.currentState}")
        }
    }


}