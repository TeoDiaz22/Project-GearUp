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

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val txtEmail = findViewById<TextInputEditText>(R.id.login_email)
        val txtPassword = findViewById<TextInputEditText>(R.id.login_password)
        val btnLogin = findViewById<Button>(R.id.login_login)
        btnLogin.setOnClickListener {
            if (TextUtils.isEmpty(txtEmail.text)) {
                Toast.makeText(this, "Ingresa un correo electronico", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(txtPassword.text)) {
                Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            login(txtEmail.text.toString(),txtPassword.text.toString())
        }

        val txtRegisterClick = findViewById<TextView>(R.id.login_txtregister)
        txtRegisterClick.setOnClickListener { openActivity(Register::class.java) }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
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

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload()
        }
    }

    fun openActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}