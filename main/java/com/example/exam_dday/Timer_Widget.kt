package com.example.exam_dday

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import kotlin.concurrent.thread

//참조 코드...
//https://mycute7.tistory.com/entry/안드로이드-스튜디오-시계위젯-만들기-Thread-Timer-TimerTask-사용
//https://blog.naver.com/PostView.nhn?blogId=mycute7&logNo=220691134341
/**
 * Implementation of App Widget functionality.
 */
class Timer_Widget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        /* 원래 있던 코드
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        */

        thread(start = true) {
            while (true) {
                for (appWidgetId in appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId)
                }
                Thread.sleep(1000)
            }
        }

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    fun  getTime(): String {
        //단순 현재시간 출력
        /*
        var now = Calendar.getInstance()
        // 시간을 받고
        val hour: Int = now.get(Calendar.HOUR_OF_DAY)
        // 분 정보도 받고
        val min: Int = now.get(Calendar.MINUTE)
        // 몇 초인지도 받아와서
        val sec: Int = now.get(Calendar.SECOND)
        return hour.toString() + ":" + min + ":" + sec
         */

        var sampleDate = "2021-06-21 09:00:00"
        var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var date = sf.parse(sampleDate)
        var today = Calendar.getInstance()
        var calcuDate = (date.time - today.time.time)/1000 - (0)*3600  // (60 * 60 * 24 * 1000)

        if(calcuDate<0) calcuDate=-calcuDate // 지났다면

        var sec = calcuDate%60
        var min = (calcuDate/60)%60
        var hour = (calcuDate/3600)%24
        var day = (calcuDate/(3600*24))
        return "$day"+"일 $hour:$min:$sec"
    }

    val widgetText = getTime()
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.timer__widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

