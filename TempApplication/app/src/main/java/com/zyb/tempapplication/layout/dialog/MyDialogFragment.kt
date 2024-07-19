package com.zyb.tempapplication.layout.dialog

import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ConvertUtils
import com.zyb.tempapplication.R
import java.lang.ref.WeakReference

class MyDialogFragment :DialogFragment() {

    private val layoutRes = 0
    private val dialogWidth = 0
    private val dialogHeight = 0
    private val dimAmount = 0.2f
    private val gravity = Gravity.CENTER
    private val isCancelableOutside = true
    private val cancelable = false
    private val animRes = 0
    private var dialogView: View? = null
    private var mPositiveButtonListener: IDialog.OnClickListener? = null
    private var mNegativeButtonListener: IDialog.OnClickListener? = null
    private val mDialog: WeakReference<IDialog>? = null
    private val titleStr: String? = null //默认标题
    private val contentStr: String? = null //默认内容
    private val positiveStr: String? = null //右边按钮文字
    private val negativeStr: String? = null //左边按钮文字
    private val showBtnLeft = false
    private var showBtnRight:kotlin.Boolean = false

    private var btn_ok: Button? = null
    private var btn_cancel:android.widget.Button? = null

    companion object{
        fun createIns() :MyDialogFragment{
            return MyDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //调用方通过xml获取view
        val view = inflater.inflate(R.layout.lib_ui_layout_dialog_default, container, false)
        dialogView = view
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positiveBtnListener = IDialog.OnClickListener{
                dialog : IDialog -> dismiss()
        }
        val negativeBtnListener = { dialog : IDialog -> dismiss() }
        dealDefaultDialog(negativeBtnListener , positiveBtnListener ,"我是标题", "你好,我们将在30分钟处理，稍后通知您订单结果！",
            true,"取消",true,"确认")
    }

    private fun dealDefaultDialog(
        positiveBtnListener:IDialog.OnClickListener ,
        negativeBtnListener: IDialog.OnClickListener,
        titleStr: String,
        contentStr: String,
        showBtnLeft: Boolean,
        negativeStr: String,
        showBtnRight: Boolean,
        positiveStr: String
    ) {
        if (dialogView == null) return
        this.mNegativeButtonListener = negativeBtnListener
        this.mPositiveButtonListener = positiveBtnListener
        btn_ok = dialogView!!.findViewById<View>(R.id.btn_ok) as Button
        btn_cancel = dialogView!!.findViewById<View>(R.id.btn_cancel) as Button

        if (showBtnRight && showBtnLeft) {
            //左右两个按钮都存在
            btn_ok?.apply {
                visibility = View.VISIBLE
                text = Html.fromHtml(if (TextUtils.isEmpty(positiveStr)) "确定" else positiveStr)
                setOnClickListener(mButtonHandler)
            }
            btn_cancel?.apply {
                visibility = View.VISIBLE
                text = Html.fromHtml(if (TextUtils.isEmpty(negativeStr)) "取消" else negativeStr)
                setOnClickListener(mButtonHandler)
            }
        } else if (showBtnRight) {
            //只有右边的按钮
            btn_ok?.apply {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.lib_ui_selector_btn_border_bg)
                text = Html.fromHtml(if (TextUtils.isEmpty(positiveStr)) "确定" else positiveStr)
                setOnClickListener(mButtonHandler)
            }
        } else if (showBtnLeft) {
            //只有左边的按钮
            btn_cancel?.apply {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.lib_ui_selector_btn_border_bg)
                text = Html.fromHtml(if (TextUtils.isEmpty(negativeStr)) "取消" else negativeStr)
                setOnClickListener(mButtonHandler)
            }
        }

        val tv_title = dialogView!!.findViewById<View>(R.id.dialog_title) as TextView
        val tv_content = dialogView!!.findViewById<View>(R.id.dialog_content) as TextView

        tv_title.visibility = if (TextUtils.isEmpty(titleStr)) View.GONE else View.VISIBLE
        tv_title.text = Html.fromHtml(if (!TextUtils.isEmpty(titleStr)) titleStr else "Title")
        if (TextUtils.isEmpty(contentStr) && mDialog != null && mDialog.get()?.getContext() != null
        ) {
            tv_title.minHeight = ConvertUtils.dp2px(100f)
            tv_title.gravity = Gravity.CENTER
            tv_title.setPadding(0, 10, 0, 0)
        }
        tv_content.visibility = if (TextUtils.isEmpty(contentStr)) View.GONE else View.VISIBLE
        tv_content.text = contentStr
        tv_content.viewTreeObserver.addOnPreDrawListener(ViewTreeObserver.OnPreDrawListener {
            val lineCount = tv_content.lineCount
            if (lineCount >= 3) {
                //超过三行居左显示
                tv_content.gravity = Gravity.START
            } else {
                //默认居中
                tv_content.gravity = Gravity.CENTER_HORIZONTAL
                if (TextUtils.isEmpty(titleStr)) {
                    tv_content.setPadding(0, 50, 0, 50)
                }
            }

            if (TextUtils.isEmpty(titleStr)) {
                //没有title，只有content
                tv_content.textSize = 18f
                if (mDialog?.get() == null || mDialog.get()?.getContext() == null || mDialog.get()
                        ?.getContext()?.resources == null
                ) return@OnPreDrawListener true
                mDialog.get()?.getContext()?.resources?.getColor(R.color.c333333)?.let {
                    tv_content.setTextColor(
                        it
                    )
                }
            }
            true
        })
    }

    private val mButtonHandler =
        View.OnClickListener { view ->
            if (view === btn_cancel) {
                if (mDialog?.get() == null) return@OnClickListener
                if (mNegativeButtonListener != null) {
                    mNegativeButtonListener?.onClick(mDialog.get()!!)
                }
            } else if (view === btn_ok) {
                if (mDialog?.get() == null) return@OnClickListener
                if (mPositiveButtonListener != null) {
                    mPositiveButtonListener?.onClick(mDialog.get()!!)
                }
            }
        }

    /**
     * 解决 Can not perform this action after onSaveInstanceState问题
     *
     * @param manager FragmentManager
     * @param tag     tag
     */
    fun showAllowingLoss(manager: FragmentManager, tag: String?) {
        try {
            val cls: Class<*> = DialogFragment::class.java
            val mDismissed = cls.getDeclaredField("mDismissed")
            mDismissed.isAccessible = true
            mDismissed[this] = false
            val mShownByMe = cls.getDeclaredField("mShownByMe")
            mShownByMe.isAccessible = true
            mShownByMe[this] = true
        } catch (e: Exception) {
            //调系统的show()方法
            show(manager, tag)
            return
        }
        val ft = manager.beginTransaction()
        ft.add(createIns(), tag)
        ft.commitAllowingStateLoss()
    }

    override fun dismiss() {
        //防止横竖屏切换时 getFragmentManager置空引起的问题：
        //Attempt to invoke virtual method 'android.app.FragmentTransaction
        //android.app.FragmentManager.beginTransaction()' on a null object reference
        if (fragmentManager == null) return
        super.dismissAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}