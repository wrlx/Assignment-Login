package com.example.oct11

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.oct11.recycler.MainRecycler
import com.example.oct11.recyclerOnline.MainRecyclerOnline
import kotlin.random.Random

class Signup : AppCompatActivity() {
    private val userList = ArrayList<UserDetails>()


    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun Context.startNewActivity(destination: Class<*>, dataMap: HashMap<String, String>) {
        val intent = Intent(this, destination)
        for ((key, value) in dataMap) {
            intent.putExtra(key, value)
        }
        startActivity(intent)
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
        val btnShowUser: TextView
        val btnShowCar: TextView

        etSignupName = findViewById(R.id.signUpName)
        etSignupEmail = findViewById(R.id.signUpEmail)
        etSignupPassword = findViewById(R.id.signUpPassword)
        btnSignUp = findViewById(R.id.signUpBtn)
        btnShowUser = findViewById(R.id.showUser)
        btnShowCar = findViewById(R.id.showCar)

        btnSignUp.setOnClickListener{
            var userName = etSignupName.text.toString()
            var email = etSignupEmail.text.toString()
            var password = etSignupPassword.text.toString()
            doDataRetrival(userName, email, password)

        }
        btnShowUser.setOnClickListener {
            val dataMap = hashMapOf(
                "message_key1" to "Sign Up Here!",
            )
            startNewActivity(MainRecycler::class.java, dataMap)
        }

        btnShowCar.setOnClickListener {
            val dataMap = hashMapOf(
                "message_key1" to "Sign Up Here!",
            )
            startNewActivity(MainRecyclerOnline::class.java, dataMap)
        }
    }
}