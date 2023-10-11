package com.example.oct11

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    fun doLoginCheck(user : String, password : String){
        val storedUserName = "jinu"
        val storedPassword = "password"

        if (user == storedUserName && password == storedPassword){
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        } else{
            val imageSet: ImageView
            imageSet = findViewById(R.id.loginFail)
            val fadeOutAnimator = ObjectAnimator.ofFloat(imageSet, "alpha", 1.0f, 0.0f)
            fadeOutAnimator.duration = 3000

            val toast = Toast
                .makeText(applicationContext,
                    "Login Failed",
                    Toast.LENGTH_SHORT).show()

            imageSet.setImageResource(R.drawable.ic_login_fail)

            fadeOutAnimator.start()

            Handler(Looper.getMainLooper()).postDelayed({
                imageSet.visibility = View.INVISIBLE
            }, fadeOutAnimator.duration)
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etUsername: EditText
        val etPassword: EditText
        val btnLogin: Button
        val btnSignup: TextView

        etUsername = findViewById(R.id.userName)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.loginButton)
        btnSignup = findViewById(R.id.toSignup)


        btnLogin.setOnClickListener {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            doLoginCheck(userName, password)
        }
        btnSignup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}