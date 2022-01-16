package com.example.nfeed.fragments2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.UserInfo
import com.example.nfeed.adapter.SavedRecyclerViewAdapter
import com.example.nfeed.network.NetworkClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.NonCancellable.children

class Saved:Fragment(R.layout.saved) {

    private lateinit var recyclerView:RecyclerView
    private lateinit var savedRecyclerViewAdapter: SavedRecyclerViewAdapter


    private val auth = FirebaseAuth.getInstance()
    private val dbSavedNews: DatabaseReference = FirebaseDatabase.getInstance().getReference("News")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)


    }



}