package com.example.project_gearup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    // ...
    // Initialize Firebase Auth
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        val txtEmail = findViewById<TextInputEditText>(R.id.register_email)
        val txtPassword = findViewById<TextInputEditText>(R.id.register_password)
        val btnRegister = findViewById<Button>(R.id.register_register)
        btnRegister.setOnClickListener {
            if (TextUtils.isEmpty(txtEmail.text)) {
                Toast.makeText(this, "Ingresa un correo electronico", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(txtPassword.text)) {
                Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            register(txtEmail.text.toString(),txtPassword.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            openActivity(Home::class.java)
        }
    }

    private fun register(email: String,password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext,
                        "Usuario registrado",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val user = auth.currentUser
                    openActivity(Home::class.java)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
                }
            }

    }

    fun openActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}