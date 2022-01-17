package com.example.nfeed.adapter

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


import androidx.navigation.Navigation
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
                val sUrlImage = news.urlToImage.toString()
                val sUrl = news.url
                val aAuthor = news.author
                val sSource = news.source.name
                val sData = news.publishedAt
                val sContent = news.content

                val nextNews =  System.currentTimeMillis().toString()

                val savedN = News(sTitle,sUrlImage,sUrl,aAuthor,sSource,sData,sContent)
                dbSavedNews.child(auth.currentUser?.uid!!).child(nextNews).setValue(savedN)

            }
            itemView.setOnClickListener{

                val gTitle = news.title
                val gContent = news.content
                val gUrlImage = news.urlToImage.toString()
                val gUrl = news.url
                val author = news.author
                val source = news.source.name
                val data = news.publishedAt

                val navController = Navigation.findNavController(itemView)

                if (author == null ){
                    val author1 = "unknown"

                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent,gUrlImage,gUrl,author1,source,data )
                    navController.navigate(action1)
                }
                else if (source == null){
                    val source1 = "News"

                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent,gUrlImage,gUrl,author,source1,data )
                    navController.navigate(action1)
                }
                else if (data == null){
                    val data1 = "  "

                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent,gUrlImage,gUrl,author,source,data1 )
                    navController.navigate(action1)
                }
                else if (gContent == null){
                    val gContent1 = "  "

                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent1,gUrlImage,gUrl,author,source,data )
                    navController.navigate(action1)
                }
                else if (gUrlImage == null){
                    val gUrlImage1 = "  "

                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent,gUrlImage1,gUrl,author,source,data )
                    navController.navigate(action1)
                }
                else{
                    val action1 = HomeDirections.actionHomeToFullInfo(gTitle,gContent,gUrlImage,gUrl,author,source,data )
                    navController.navigate(action1)
                }




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