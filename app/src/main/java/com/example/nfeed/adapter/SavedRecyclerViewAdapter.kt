package com.example.nfeed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.fragments2.HomeDirections
import com.example.nfeed.fragments2.SavedDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SavedRecyclerViewAdapter(private var list: List<News>): RecyclerView.Adapter<SavedRecyclerViewAdapter.SavedNewsViewHolder>() {


    class SavedNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)



        fun setData(savedNews: News) {

            Glide.with(itemView.context)
                .load(savedNews.imageUrl)
                .centerCrop()
                .into(imageView)


            itemView.setOnClickListener {

                val gTitle = savedNews.title.toString()
                val gContent = savedNews.content.toString()
                val gUrlImage = savedNews.imageUrl.toString()
                val gUrl = savedNews.url.toString()
                val author = savedNews.author.toString()
                val source = savedNews.source.toString()
                val data = savedNews.data.toString()

                val navController = Navigation.findNavController(itemView)


                val action1 = SavedDirections.actionSavedToFullInfo(
                    gTitle,
                    gContent,
                    gUrlImage,
                    gUrl,
                    author,
                    source,
                    data
                )
                navController.navigate(action1)


            }

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


    }

    override fun getItemCount() = list.size


}