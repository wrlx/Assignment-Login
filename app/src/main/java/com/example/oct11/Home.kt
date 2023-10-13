package com.example.oct11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val btnFirstFragment = findViewById<Button>(R.id.btnFragmentOne)
        val btnSecondFragment = findViewById<Button>(R.id.btnFragmentTwo)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, firstFragment).commit()
        }

        btnFirstFragment.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, firstFragment).addToBackStack(null).commit()
//                btnSecondFragment.setBackgroundColor(getResources().getColor(R.color.));
            }
        }

        btnSecondFragment.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, secondFragment).addToBackStack(null).commit()
            }
        }

    }
}