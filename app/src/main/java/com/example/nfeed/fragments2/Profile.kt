package com.example.nfeed.fragments2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nfeed.MainActivity
import com.example.nfeed.R
import com.example.nfeed.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class Profile:Fragment(R.layout.profile) {
    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")

    private lateinit var name:TextView
    private lateinit var image: ImageView
    private lateinit var lognOut: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.name)
        image = view.findViewById(R.id.image)
        lognOut = view.findViewById(R.id.lognOut)

        dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userinfo = snapshot.getValue(UserInfo::class.java)?: return

                name.text = userinfo.full_name

                Glide.with(this@Profile)
                    .load(userinfo.url)
                    .centerCrop()
                    .into(image)

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        lognOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)



        }





    }
}