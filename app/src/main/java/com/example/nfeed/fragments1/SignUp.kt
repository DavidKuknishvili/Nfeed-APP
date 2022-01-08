package com.example.nfeed.fragments1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.navigation.fragment.findNavController

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.nfeed.R
import com.example.nfeed.UserInfo
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp:Fragment(R.layout.signup) {

    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")


    private lateinit var email: EditText
    private lateinit var password1: EditText
    private lateinit var password2: EditText
    private lateinit var signUp: Button

    private lateinit var signIn: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        email = view.findViewById(R.id.email)
        password1 = view.findViewById(R.id.password1)
        password2 = view.findViewById(R.id.password2)
        signUp = view.findViewById(R.id.signUp)

        signIn = view.findViewById(R.id.signIn)


        signUp.setOnClickListener {
            val Email = email.text.toString().trim()
            val Password1 = password1.text.toString().trim()
            val Password2 = password2.text.toString().trim()



            if(Email.isEmpty() || Password1.isEmpty() || Password2.isEmpty()) {
                Toast.makeText(requireContext(), "It is required to fill in all the fields ", Toast.LENGTH_SHORT).show()

            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                Toast.makeText(requireContext(), "E-mail is not correct", Toast.LENGTH_SHORT).show()

            } else if(Password1.length < 8) {
                Toast.makeText(requireContext(), "Password must be longer than 8 characters", Toast.LENGTH_SHORT).show()

            }
            else if(Password1 != Password2) {
                Toast.makeText(requireContext(), "Passwords must be same", Toast.LENGTH_SHORT).show()
            }

            else{

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(Email, Password1)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            val successSnakebar = Snackbar.make(requireView(),"You have successfully registered", Snackbar.LENGTH_LONG)
                            successSnakebar.setBackgroundTint(Color.GREEN).show()

                            findNavController().navigate(R.id.action_signUp_to_editProfile)





                        }
                        else{
                            val errorSnackbar = Snackbar.make(requireView(),"Tray again", Snackbar.LENGTH_LONG)
                            errorSnackbar.setBackgroundTint(Color.RED).show()

                        }

                    }

            }


        }




        signIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)

        }



    }

}