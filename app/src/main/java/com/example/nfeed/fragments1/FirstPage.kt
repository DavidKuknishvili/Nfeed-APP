package com.example.nfeed.fragments1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.nfeed.R
import com.google.firebase.auth.FirebaseAuth

class FirstPage: Fragment(R.layout.firstpage) {


    private lateinit var signUp: Button
    private lateinit var signIn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(FirebaseAuth.getInstance().currentUser != null){
            findNavController().navigate(R.id.action_firstPage_to_home)
        }
        super.onViewCreated(view, savedInstanceState)

        signUp = view.findViewById(R.id.signUp)
        signIn = view.findViewById(R.id.signIn)

        val navController = Navigation.findNavController(view)


        signIn.setOnClickListener {

            val action = FirstPageDirections.actionFirstPageToSignIn()
            navController.navigate(action)

        }

        signUp.setOnClickListener {

            val action = FirstPageDirections.actionFirstPageToSignUp()
            navController.navigate(action)

        }








    }
}