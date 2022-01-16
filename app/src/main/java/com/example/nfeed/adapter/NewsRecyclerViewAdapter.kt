package com.example.nfeed.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.model.Article
import com.example.nfeed.model.Model
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


import androidx.fragment.app.Fragment
import com.example.nfeed.fragments2.FullInfo
import com.example.nfeed.MainActivity


import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.nfeed.Main
import com.example.nfeed.fragments2.Home
import com.example.nfeed.fragments2.HomeDirections


class NewsRecyclerViewAdapter(private var list: Model): RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)

        val add:Button = itemView.findViewById(R.id.add)

        fun setData(news: Article){

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .centerCrop()
                .into(imageView)




            val auth = FirebaseAuth.getInstance()
            add.setOnClickListener {


                val dbSavedNews: DatabaseReference = FirebaseDatabase.getInstance().getReference("News")

                val sTitle = news.title
                val sDescription = news.description
                val sUrl = news.urlToImage.toString()

                val nextNews =  System.currentTimeMillis().toString()

                val savedN = News(sTitle,sDescription,sUrl)
                dbSavedNews.child(auth.currentUser?.uid!!).child(nextNews).setValue(savedN)

            }
            itemView.setOnClickListener{

                val sTitle = news.title
                val sDescription = news.description
                val sUrl = news.urlToImage.toString()

                val navController = Navigation.findNavController(itemView)


                val action1 = HomeDirections.actionHomeToFullInfo(sTitle,sDescription,sUrl)
                navController.navigate(action1)



            }



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



    }

    override fun getItemCount() = list.articles.size



}