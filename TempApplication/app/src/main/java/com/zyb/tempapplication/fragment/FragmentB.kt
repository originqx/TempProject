package com.zyb.tempapplication.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zyb.tempapplication.R


class FragmentB : Fragment() {
    companion object{
        fun createInstance():FragmentB{
            return FragmentB()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutInflater.inflate(R.layout.fragment_a, null)

    }
}