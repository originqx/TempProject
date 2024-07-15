package com.zyb.tempapplication.layout

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zyb.tempapplication.R
import java.util.Calendar


class LineLayoutActivity: AppCompatActivity() {


    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton

    private lateinit var videoView: VideoView


    private lateinit var checkbox1: CheckBox
    private lateinit var checkbox2: CheckBox
    private lateinit var status1: TextView
    private lateinit var status2: TextView

    //定义五个当前时间的变量
    private var year = 0
    private var month = 0
    private var day = 0
    private var hour = 0
    private var minute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linearlayout_activity)
        initView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        val btn = findViewById<Button>(R.id.button)
        val btn2 = findViewById<Button>(R.id.button2)

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        //点击事件
        btn.setOnClickListener { //输出对应的信息
            Log.i("再见孙悟空", "点击事件触发:  ")
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Title") // 设置对话框标题
            builder.setMessage("Message") // 设置对话框消息内容
            builder.setIcon(R.drawable.radio_btn_selector) // 设置对话框图标
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // 点击确定按钮后的操作
                Log.i("AlertDialog", "点击事件触发: OK")
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                // 点击取消按钮后的操作
                Log.i("AlertDialog", "点击事件触发: CANNEL")
            })
            builder.create().show()

        }

        //点击事件
        btn2.setOnClickListener { //输出对应的信息
            Log.i("再见孙悟空", "点击事件触发:  ")
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Title") // 设置对话框标题
            progressDialog.setMessage("Message") // 设置对话框消息内容
            progressDialog.setIcon(R.drawable.radio_btn_selector) // 设置对话框图标
            progressDialog.progress = 22
            progressDialog.max = 70
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER) // 设置进度条样式，包括STYLE_SPINNER（圆形旋转）和STYLE_HORIZONTAL（水平进度条）
            progressDialog.isIndeterminate = true // 设置是否为不确定模式（即不显示具体进度）
            progressDialog.show()
        }


        //长按事件
        btn.setOnLongClickListener {
            Log.i("再见孙悟空", "长按事件触发: ")
            false
        }


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (radioButton1.isChecked) {
                radioButton1.setTextColor(Color.RED)
            } else {
                radioButton1.setTextColor(Color.parseColor("#000000"))
            }
            if (radioButton2.isChecked) {
                radioButton2.setTextColor(Color.RED)
            } else {
                radioButton2.setTextColor(Color.parseColor("#000000"))
            }
        }


        //触摸事件
        btn.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {}
                MotionEvent.ACTION_UP -> {}
                MotionEvent.ACTION_MOVE -> {}
            }
            false
        }


        val datePicker = findViewById<View>(R.id.datePicker) as DatePicker
        val timePicker = findViewById<View>(R.id.timePicker) as TimePicker

        //获取当前日期/时间
        val calendar: Calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)

        //为DatePicker添加监听事件
        datePicker.init(
            year, month, day
        ) { view, year, monthOfYear, dayOfMonth ->
            this@LineLayoutActivity.year = year
            this@LineLayoutActivity.month = month
            this@LineLayoutActivity.day = day
            //显示用户选择的 日期 和 时间
            Toast.makeText(
                this@LineLayoutActivity,
                year.toString() + "年" + month + "月" + day + "日" + hour + "时" + minute + "分",
                Toast.LENGTH_SHORT
            ).show()
        }

        //TimePicker选择监听器
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            this@LineLayoutActivity.hour =
                hourOfDay
            this@LineLayoutActivity.minute = minute
        }
        val calendarview = findViewById<View>(R.id.calendarview) as CalendarView

        calendarview.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Toast.makeText(
                this@LineLayoutActivity,
                "您选择的时间是：" + year + "年" + month + "月" + dayOfMonth + "日",
                Toast.LENGTH_SHORT
            ).show()
        }



        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.max = 20
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当SeekBar的进度改变时触发该方法
                // 可以根据进度值进行相应的操作
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // 当用户开始拖动SeekBar时触发该方法
                Log.i("seekBar: onStartTrackingTouch",seekBar.progress.toString() )
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // 当用户停止拖动SeekBar时触发该方法
                Toast.makeText(
                    this@LineLayoutActivity,
                    "seekBar: onStartTrackingTouch: ${seekBar.progress}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.isIndeterminate = false
        progressBar.max = 30 // 设置最大值
        progressBar.progress = 50 // 设置当前进度


        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.numStars = 3 // 设置最大星星数量
        ratingBar.stepSize = 0.5f // 设置步长，即每次选择增加或减少的值
        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                // 当用户改变评级时触发该方法
                Toast.makeText(
                    this@LineLayoutActivity,
                    "seekBar: onStartTrackingTouch: $rating",
                    Toast.LENGTH_SHORT
                ).show()
            }


        val webView = findViewById<WebView>(R.id.webview)
        webView.loadUrl("https://www.baidu.com") // 加载指定的URL

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url) // 在WebView中加载URL
                return true
            }
        }
        webView.getSettings().setJavaScriptEnabled(true);


        // 获取VideoView实例
        videoView = findViewById<View>(R.id.videoview) as VideoView


        // 设置视频路径（可以是本地路径或网络URL）
        val videoPath = "https://sample-videos.com/video321/mkv/720/big_buck_bunny_720p_1mb.mkv"
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)


        // 创建媒体控制器(MediaController)
        val mediaController: MediaController = MediaController(this)
        mediaController.setAnchorView(videoView)


        // 关联媒体控制器
        videoView.setMediaController(mediaController)


        // 开始播放视频
        videoView.start()




        checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        status1 = findViewById<TextView>(R.id.status1)
        status2 = findViewById<TextView>(R.id.status2)

        checkbox1.setOnCheckedChangeListener { buttonView, isChecked ->
            updateStatusText(
                status1,
                isChecked
            )
        }

        checkbox2.setOnCheckedChangeListener { buttonView, isChecked ->
            updateStatusText(
                status2,
                isChecked
            )
        }


    }


    private fun updateStatusText(textView: TextView, isChecked: Boolean) {
        if (isChecked) {
            textView.text = "已选中"
        } else {
            textView.text = "未选中"
        }
    }


}