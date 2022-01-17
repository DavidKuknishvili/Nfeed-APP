package com.example.nfeed.fragments2

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.adapter.SavedRecyclerViewAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Saved:Fragment(R.layout.saved) {

    private lateinit var recyclerView:RecyclerView
    private lateinit var image: ImageView
    private lateinit var text: TextView
    private lateinit var remove: Button
    private lateinit var savedRecyclerViewAdapter: SavedRecyclerViewAdapter


    private lateinit var newsArrayList: ArrayList<News>

    private val auth = FirebaseAuth.getInstance()
    private val dbSavedNews = FirebaseDatabase.getInstance().getReference("News")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        image = view.findViewById(R.id.image)
        text = view.findViewById(R.id.text)
        remove = view.findViewById(R.id.remove)

        remove.setOnClickListener {

            val dbSavedNews: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("News")
            val auth = FirebaseAuth.getInstance()
            dbSavedNews.child(auth.currentUser?.uid!!).removeValue()

        }

        newsArrayList = arrayListOf<News>()
        getSavedNews()
        recyclerView.setHasFixedSize(true)
    }

    private fun getSavedNews() {

        dbSavedNews.child(auth.currentUser?.uid!!).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    image.visibility = GONE
                    text.visibility = GONE

                    for(savedNewsSnapshot in snapshot.children){
                        val savedNews = savedNewsSnapshot.getValue(News::class.java)
                        newsArrayList.add(savedNews!!)

                    }
                    recyclerView.adapter = SavedRecyclerViewAdapter(newsArrayList)
                    recyclerView.layoutManager = LinearLayoutManager(context)


                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


}