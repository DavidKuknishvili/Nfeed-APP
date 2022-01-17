package com.example.nfeed.fragments2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.icu.text.CaseMap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
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
    private lateinit var readMore: TextView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)
        readMore = view.findViewById(R.id.readMore)




        view.findViewById<TextView>(R.id.title).text =
            FullInfoArgs.fromBundle(requireArguments()).gTitle

        view.findViewById<TextView>(R.id.info).text =
            FullInfoArgs.fromBundle(requireArguments()).gDescription
        view.findViewById<TextView>(R.id.data).text =
            FullInfoArgs.fromBundle(requireArguments()).gdata
        view.findViewById<TextView>(R.id.source).text =
            FullInfoArgs.fromBundle(requireArguments()).gsource
        view.findViewById<TextView>(R.id.author).text =
            FullInfoArgs.fromBundle(requireArguments()).gauthor

        val urlImage = FullInfoArgs.fromBundle(requireArguments()).gUrlImage

        Glide.with(this@FullInfo)
                    .load(urlImage)
                    .centerCrop()
                    .into(image)

        val url = FullInfoArgs.fromBundle(requireArguments()).gUrL

        readMore.setOnClickListener {
            val uri =  Uri.parse(url)
            val browser = Intent(Intent.ACTION_VIEW, uri)
            browser.setPackage("com.browser.android")
            try {
                startActivity(browser)
            }catch (e: ActivityNotFoundException){
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }

        }




    }
}