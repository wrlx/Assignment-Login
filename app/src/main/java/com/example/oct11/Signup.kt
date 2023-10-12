package com.example.oct11

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Signup : AppCompatActivity() {
    private val userList = ArrayList<UserDetails>()


    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun doDataRetrival(name: String, email: String, password: String){
        val languageLV: ListView
        languageLV = findViewById(R.id.displayList)

        if(name != "" && email != "" && password != ""){
            var userId = generateRandomUserId(5)
            val details = UserDetailsClass().apply {
                uName = name
                uEmail = email
                uPassword = password
            }
            details.also {
                it.id = userId
            }
            details.let{
                userList.add(UserDetails(it.id, it.uName, it.uEmail, it.uPassword))
                loginData.set(it.uName, UserDetails(it.id, it.uName, it.uEmail, it.uPassword))

            }


            val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                this@Signup,
                android.R.layout.simple_list_item_1,
                userList as List<String?>
            )
            languageLV.adapter = adapter

            adapter.notifyDataSetChanged()

        } else {
            applicationContext.showToast("Fields shouldn't be blank")
        }
    }


    fun generateRandomUserId(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random.Default
        val userId = StringBuilder()

        repeat(length) {
            val randomIndex = random.nextInt(chars.length)
            val randomChar = chars[randomIndex]
            userId.append(randomChar)
        }
        return userId.toString()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val etSignupName: EditText
        val etSignupEmail: EditText
        val etSignupPassword: EditText
        val btnSignUp: Button

        etSignupName = findViewById(R.id.signUpName)
        etSignupEmail = findViewById(R.id.signUpEmail)
        etSignupPassword = findViewById(R.id.signUpPassword)
        btnSignUp = findViewById(R.id.signUpBtn)

        btnSignUp.setOnClickListener{
            var userName = etSignupName.text.toString()
            var email = etSignupEmail.text.toString()
            var password = etSignupPassword.text.toString()
            doDataRetrival(userName, email, password)

        }

    }
}