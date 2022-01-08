package com.example.nfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(FirebaseAuth.getInstance().currentUser != null){
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}