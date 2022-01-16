package com.example.nfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class Main : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        supportActionBar?.hide()



        navView = findViewById(R.id.bottomNavigationView)

        val controller = findNavController(R.id.fragment_container)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.saved,
                R.id.profile,
            )
        )
        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)






    }

}