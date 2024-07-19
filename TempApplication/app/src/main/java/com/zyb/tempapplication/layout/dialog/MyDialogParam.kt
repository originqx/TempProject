package com.zyb.tempapplication.layout.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ScreenUtils
import com.zyb.tempapplication.R

class MyDialogParam {
    var fragmentManager: FragmentManager? = null
    var layoutRes: Int = 0
    var dialogWidth: Int= 0
    var dialogHeight: Int = 0
    var dimAmount: Float = 0.4f
    var gravity: Int = Gravity.CENTER
    var isCancelableOutside: Boolean = true
    var cancelable: Boolean = true
    var dialogView: View? = null
    var context: Context? = null
    var positiveBtnListener: IDialog.OnClickListener? = null
    var negativeBtnListener: IDialog.OnClickListener? = null
    var titleStr: String? = null
    var contentStr: String? = null
    var positiveStr: String? = null
    var negativeStr: String? = null
    var showBtnLeft: Boolean = true
    var showBtnRigth: Boolean = true
    var animRes : Int = R.style.translate_style

    /**
     * 设置默认Dialog的配置
     */
    private fun setDefaultOption() {
        this.cancelable = false
        this.isCancelableOutside = false
        this.gravity = Gravity.CENTER
        this.layoutRes = R.layout.lib_ui_layout_dialog_default
        this.dimAmount = 0.5f
        this.dialogWidth = (ScreenUtils.getScreenWidth() * 0.85f).toInt()
        this.dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT
    }
}