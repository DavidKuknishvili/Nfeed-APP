package com.example.nfeed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nfeed.News
import com.example.nfeed.R

class NewsRecyclerViewAdapter(private var list: List<News>): RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView
        val title: TextView
        val info: TextView

        init {
            imageView = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            info = itemView.findViewById(R.id.info)
        }

        fun setData(news: News){
            Glide.with(itemView.context)
                .load(news.imageUrl)
                .centerCrop()
                .into(imageView)

            title.text = news.title
            info.text = news.info

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = list[position]
        holder.setData(news)

    }

    override fun getItemCount() = list.size

}