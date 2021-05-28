package com.android.example.rssreader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter(private val topicA: List<FeedTopic>) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryView: LinearLayout = itemView.findViewById<LinearLayout>(R.id.screen1entry)
        val topicView: TextView = itemView.findViewById<TextView>(R.id.topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.feed_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.topicView.text = topicA[position].topic


        holder.topicView.setOnClickListener {
            val intent = Intent(it.context, FeedActivity::class.java)
            val topic = FeedTopic(topicA[position].topic, topicA[position].link)
            intent.putExtra("feed", topic)
            startActivity(it.context, intent, null)
        }
    }
    override fun getItemCount(): Int = topicA.size
}
