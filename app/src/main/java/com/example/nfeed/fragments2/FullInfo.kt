package com.example.nfeed.fragments2

import android.icu.text.CaseMap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FullInfo:Fragment(R.layout.full_news) {
    private lateinit var image: ImageView

    private val auth = FirebaseAuth.getInstance()
    private val dbGetNews: DatabaseReference = FirebaseDatabase.getInstance().getReference("GETNEWS")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)


        view.findViewById<TextView>(R.id.title).text =
            FullInfoArgs.fromBundle(requireArguments()).sTitle

        view.findViewById<TextView>(R.id.info).text =
            FullInfoArgs.fromBundle(requireArguments()).sDescription

        val url = FullInfoArgs.fromBundle(requireArguments()).sUrl

        Glide.with(this@FullInfo)
                    .load(url)
                    .centerCrop()
                    .into(image)



    }
}