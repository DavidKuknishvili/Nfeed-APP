package com.example.nfeed.fragments1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.nfeed.Main
import com.example.nfeed.MainActivity
import com.example.nfeed.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.MainScope

class SignIn:Fragment(R.layout.signin) {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    private lateinit var sinUp: TextView
    private lateinit var forgotPassword: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        signIn = view.findViewById(R.id.signIn)
        sinUp = view.findViewById(R.id.sinUp)
        forgotPassword = view.findViewById(R.id.forgotPassword)

        val navController = Navigation.findNavController(view)

        signIn.setOnClickListener {
            val Email = email.text.toString().trim()
            val Password = password.text.toString().trim()


            if(Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(requireContext(), "It is required to fill in all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            val intent = Intent (getActivity(), Main::class.java)
                            getActivity()?.startActivity(intent)



                        }
                        else{
                            val errorSnackbar = Snackbar.make(requireView(),"Tray again", Snackbar.LENGTH_LONG)
                            errorSnackbar.setBackgroundTint(Color.RED).show()                        }

                    }
            }



        }






        forgotPassword.setOnClickListener{
            findNavController().navigate(R.id.action_signIn_to_resetPassword)

        }
        sinUp.setOnClickListener{
            findNavController().navigate(R.id.action_signIn_to_signUp)

        }

    }
}