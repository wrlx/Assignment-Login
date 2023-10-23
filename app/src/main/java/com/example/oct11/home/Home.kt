package com.example.oct11.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.oct11.R
import com.example.oct11.carmvvm.ManufactureMvvm
import com.example.oct11.quote.QuoteFragment
import com.example.oct11.recycleronlineapi.manufactureview.ManufactureFragment

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val etMessageDisplay: TextView
        val etEmailDisplay: TextView
        val firstMessage = intent.getStringExtra("message_key1")
        val secondMessage = intent.getStringExtra("message_key2")
        etMessageDisplay = findViewById<TextView>(R.id.welcomeMessage)
        etEmailDisplay = findViewById<TextView>(R.id.userEmail)
        etMessageDisplay.text = firstMessage
        etEmailDisplay.text = secondMessage

        val firstFragment = QuoteFragment()
        val secondFragment = ManufactureMvvm()
        val btnFirstFragment = findViewById<Button>(R.id.btnFragmentOne)
        val btnSecondFragment = findViewById<Button>(R.id.btnFragmentTwo)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, firstFragment).commit()
        }

        btnFirstFragment.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, firstFragment).addToBackStack(null).commit()

            }
        }

        btnSecondFragment.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, secondFragment).addToBackStack(null).commit()
            }
        }

    }
}