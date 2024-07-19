package com.zyb.tempapplication.layout.dialog

import android.content.Context

interface IDialog {

    fun interface OnClickListener{
        fun onClick(dialog: IDialog)
    }

    interface onDismissListener{
        fun onDismiss(dialog: IDialog)
    }

    fun getContext(): Context?
}