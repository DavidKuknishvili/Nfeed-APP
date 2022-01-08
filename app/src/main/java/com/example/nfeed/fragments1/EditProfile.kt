package com.example.nfeed.fragments1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nfeed.Main
import com.example.nfeed.R
import com.example.nfeed.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProfile:Fragment(R.layout.edit_profile) {
    private lateinit var full_name: EditText
    private lateinit var url: EditText
    private lateinit var save: Button
    private lateinit var image: ImageView

    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        full_name = view.findViewById(R.id.full_name)
        url = view.findViewById(R.id.url)
        save = view.findViewById(R.id.save)
        image = view.findViewById(R.id.image)

        save.setOnClickListener {
            val fullName = full_name.text.toString()
            val url = url.text.toString()

            val userInfo = UserInfo(fullName,url)
            dbUserInfo.child(auth.currentUser?.uid!!).setValue(userInfo)

            findNavController().navigate(R.id.action_editProfile_to_signIn)




        }


    }
}