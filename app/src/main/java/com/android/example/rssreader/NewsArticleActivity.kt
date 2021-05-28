package com.android.example.rssreader

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewsArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article)

        title = ""

        // TODO PHASE 2
        val dateview = findViewById<TextView>(R.id.date)
                val DateList: List<String> = intent.getStringExtra("date")!!.split(" ")
        val day:String = DateList[1]
        val month: String = when (DateList[2]) {
            "Jan" -> "January"
            "Feb" -> "February"
            "Mar" -> "February"
            "Apr" -> "April"
            "May" -> "May"
            "June" -> "June"
            "July" -> "July"
            "Aug" -> "August"
            "Sept" -> "September"
            "Oct" -> "October"
            "Nov" -> "November"
            else -> "December"
        }
        val year: String = DateList[3]
        val time: String = DateList[4]
        val timeList: List<String> = time.split(":")
        var hr: String = timeList[0].toInt().toString()
        var apm: String = "am"
        when {
            timeList[0].toInt() > 12 -> {
                hr = (timeList[0].toInt() - 12).toString()
                apm = "pm"
            }
            timeList[0].toInt() == 12 -> {
                apm = "pm"
            }
            else -> {
                hr = timeList[0].toInt().toString()
                apm = "am"
            }
        }
        val min: String = timeList[1]

        dateview.text = "$month $day, $year - $hr:$min $apm"

        val titleview = findViewById<TextView>(R.id.title)
        titleview.text = intent.getStringExtra("title")

        val summaryview = findViewById<TextView>(R.id.summary)
        summaryview.text = intent.getStringExtra("description")

        // click button
        val news = findViewById<Button>(R.id.visit)
        news.setOnClickListener {
            val webpage: Uri = Uri.parse(intent.getStringExtra("url"))
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent,null)
        }
    }
}
