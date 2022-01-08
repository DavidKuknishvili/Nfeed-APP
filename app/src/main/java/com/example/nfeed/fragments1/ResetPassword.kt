package com.example.nfeed.fragments1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nfeed.R
import com.google.firebase.auth.FirebaseAuth

class ResetPassword: Fragment(R.layout.resetpassword) {

    private lateinit var email: EditText
    private lateinit var send: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.email)
        send = view.findViewById(R.id.send)

        send.setOnClickListener {
            val Email = email.text.toString()

            if (Email.isEmpty() || !Email.contains('@')) {
                Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(Email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Check Your Email", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_resetPassword_to_signIn)

                    } else {
                        Toast.makeText(requireContext(), "Tray again", Toast.LENGTH_SHORT).show()

                    }

                }
        }
//        back.setOnClickListener {
//            findNavController().navigate(R.id.action_resetPassword_to_signIn)
//
//        }

    }

}