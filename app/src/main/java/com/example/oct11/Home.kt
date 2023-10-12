package com.example.oct11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val etMessageDisplay: TextView
        val receivedMessage = intent.getStringExtra("message_key")
        etMessageDisplay = findViewById<TextView>(R.id.welcomeMessage)
        etMessageDisplay.text = receivedMessage
    }
}