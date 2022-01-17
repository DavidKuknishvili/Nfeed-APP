package com.example.nfeed.fragments1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nfeed.Main
import com.example.nfeed.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ChangePassword:Fragment(R.layout.change_password) {

    private lateinit var changePassword: Button
    private lateinit var email: TextView
    private lateinit var password: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changePassword = view.findViewById(R.id.changePassword)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)

        changePassword.setOnClickListener {
            val Email = email.text.toString().trim()
            val Password = password.text.toString().trim()

            if(Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(requireContext(), "It is required to fill in all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().currentUser!!
                    .updatePassword(Password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            val intent = Intent (getActivity(), Main::class.java)
                            getActivity()?.startActivity(intent)
                            val errorSnackbar = Snackbar.make(requireView(),"Your password is successfully changed", Snackbar.LENGTH_LONG)
                            errorSnackbar.setBackgroundTint(Color.GREEN).show()



                        }
                        else{
                            val errorSnackbar = Snackbar.make(requireView(),"Tray again", Snackbar.LENGTH_LONG)
                            errorSnackbar.setBackgroundTint(Color.RED).show()
                        }

                    }

            }
        }
    }
}
