package com.example.oct11

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
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

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


    fun doLoginCheck(user : String, password : String){

        if (loginData.containsKey(user)) {
            val keyToRetrieve = user
            val retrievedObject = loginData[keyToRetrieve]
            retrievedObject.let {
                if(password == it?.password){
                    applicationContext.showToast("Login Success")
                    val message = "Hello, " + user
                    val intent = Intent(this, Home::class.java)
                    intent.putExtra("message_key", message)
                    startActivity(intent)

                }else{

                    applicationContext.showToast("Login Failed")

                    val imageSet: ImageView
                    imageSet = findViewById(R.id.loginFail)

                    val fadeOutAnimator = ObjectAnimator.ofFloat(imageSet, "alpha", 1.0f, 0.0f)
                    fadeOutAnimator.duration = 3000
                     imageSet.setImageResource(R.drawable.ic_login_fail)
                    fadeOutAnimator.start()
                     Handler(Looper.getMainLooper()).postDelayed({
                        imageSet.visibility = View.INVISIBLE
                    }, fadeOutAnimator.duration)
                }
            }
        } else {
            applicationContext.showToast("User doesn't exist")
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