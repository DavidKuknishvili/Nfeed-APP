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
import com.example.nfeed.model.Article
import com.example.nfeed.model.Model

class NewsRecyclerViewAdapter(private var list: Model): RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val info: TextView = itemView.findViewById(R.id.info)

        fun setData(news: Article){
            Glide.with(itemView.context)
                .load(news.urlToImage)
                .centerCrop()
                .into(imageView)



        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = list.articles[position]
        holder.setData(news)
        holder.title.text = news.title
        holder.info.text = news.description

    }

    override fun getItemCount() = list.articles.size

}