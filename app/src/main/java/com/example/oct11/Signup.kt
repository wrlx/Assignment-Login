package com.example.oct11

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class Signup : AppCompatActivity() {
    private val userList = ArrayList<UserDetails>()
    var userDetails = ArrayList<String>()

    fun doDataRetrival(name: String, email: String, password: String){
        val languageLV: ListView
        languageLV = findViewById(R.id.displayList)

        if(name != null && email != null && password != null){
            var userId = generateRandomUserId(5)
            userList.add(UserDetails(userId, name, email, password))
//            var temp = userList.size
//            var limit = temp-1
//            val toast = Toast
//                .makeText(applicationContext,
//                    "$userList, $limit",
//                    Toast.LENGTH_SHORT).show()

//            for(i in 0..limit){
//                userIdDisplay = userList[i].id
//                userNameDisplay = userList[i].name
//                userEmailDisplay = userList[i].email
//                userPasswordDisplay = userList[i].password
//                userDetails = userIdDisplay + ", " + userNameDisplay + ", " + userEmailDisplay + ", " + userPasswordDisplay
//            }
            val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                this@Signup,
                android.R.layout.simple_list_item_1,
                userList as List<String?>
            )
            languageLV.adapter = adapter

            adapter.notifyDataSetChanged()
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