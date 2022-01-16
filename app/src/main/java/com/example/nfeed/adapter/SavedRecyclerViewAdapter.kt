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
import com.example.nfeed.model.Model
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.channels.ticker

class SavedRecyclerViewAdapter(private var list: ArrayList<News>): RecyclerView.Adapter<SavedRecyclerViewAdapter.SavedNewsViewHolder>() {


    class SavedNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val info: TextView = itemView.findViewById(R.id.info)

        fun setData(savedNews: News){

            Glide.with(itemView.context)
                .load(savedNews.imageUrl)
                .centerCrop()
                .into(imageView)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_item, parent,false)
        return SavedNewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val savedNews = list[position]
        holder.setData(savedNews)
        holder.title.text = savedNews.title
        holder.info.text = savedNews.info

    }

    override fun getItemCount() = list.size


}