package com.android.example.rssreader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item

class RSSFeedAdaptor(private val mRSSFeed: MutableList<Item>) : RecyclerView.Adapter<RSSFeedAdaptor.RSSViewHolder>() {
    class RSSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryView: LinearLayout = itemView.findViewById<LinearLayout>(R.id.screen2entry)
        val dateView: TextView = itemView.findViewById<TextView>(R.id.date2)
        val titleView: TextView = itemView.findViewById<TextView>(R.id.title2)
        val desView: TextView = itemView.findViewById<TextView>(R.id.description2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rss_feed_item, parent, false)
        return RSSViewHolder(view)
    }

    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {
        val DateList: List<String> = mRSSFeed[position].pubDate.split(" ")
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

        holder.dateView.text = "$month $day, $year"
        holder.titleView.text = mRSSFeed[position].title
        holder.desView.text = mRSSFeed[position].description

        holder.titleView.setOnClickListener {
            val intent = Intent(it.context, NewsArticleActivity::class.java)
            intent.putExtra("date",mRSSFeed[position].pubDate)
            intent.putExtra("title",mRSSFeed[position].title)
            intent.putExtra("description",mRSSFeed[position].description)
            intent.putExtra("url",mRSSFeed[position].link)
            startActivity(it.context,intent,null)
        }
    }
    override fun getItemCount(): Int = mRSSFeed.size
}
