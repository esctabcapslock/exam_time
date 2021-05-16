package com.example.exam_dday

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textfirst : TextView = findViewById(R.id.textview_first)
        //로그를 출력해보자.
        Log.d("안녕", "안녕")

        var sampleDate = "2021-06-21 09:00:00"
        var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var date = sf.parse(sampleDate)
        var today = Calendar.getInstance()
        Log.d("로그 - 현재 시간대",Calendar.ZONE_OFFSET.toString())


        var calcuDate = (date.time - today.time.time)/1000 - (0)*3600  // (60 * 60 * 24 * 1000)

        if (calcuDate>0){
            var sec = calcuDate%60
            var min = (calcuDate/60)%60
            var hour = (calcuDate/3600)%24
            var day = (calcuDate/(3600*24))

            textfirst.text = "기말고사까지 $day"+"일 $hour:$min:$sec 남음"
        }else{
            calcuDate=-calcuDate
            var sec = calcuDate%60
            var min = (calcuDate/60)%60
            var hour = (calcuDate/3600)%24
            var day = (calcuDate/(3600*24))

            textfirst.text = "기말고사 시작 후 $day"+"일 $hour:$min:$sec 지남"
        }


        //출처: https://shary1012.tistory.com/217 [노마의 낡은 수첩]
    }
}