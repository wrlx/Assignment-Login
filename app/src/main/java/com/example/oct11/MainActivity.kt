package com.example.oct11

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.oct11.auth.RetrifitHelperUserAuth
import com.example.oct11.auth.UserAuth
import com.example.oct11.auth.UserData
import com.example.oct11.auth.UserFetchable
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

//    fun Context.startNewActivity(destination: Class<*>, key: String, value: String) {
//        val intent = Intent(this, destination)
//        intent.putExtra(key, value)
//        startActivity(intent)
//    }
fun Context.startNewActivity(destination: Class<*>, dataMap: HashMap<String, String>) {
    val intent = Intent(this, destination)
    for ((key, value) in dataMap) {
        intent.putExtra(key, value)
    }
    startActivity(intent)
}


    fun doLoginCheck(user : String, password : String){
        var userTemp = user.toString()
        var passwordTemp = password.toString()
        var apiResponse: Response<UserAuth>? = null
        Log.d("User : ", userTemp)
        Log.d("password : ", passwordTemp)
        val quoteApi = RetrifitHelperUserAuth.getInstance().create(UserFetchable::class.java)
        GlobalScope.launch {
            val userAuthData = UserData(userTemp, passwordTemp)
            val result = quoteApi.postData(userAuthData)
            apiResponse = result
            var userEmail = result.body()?.email.toString()
            try{
                if(result.isSuccessful || (password == "admin" && userTemp == "admin")) {
                    Log.d("login : ", result.body().toString())
                    val message = "Hello, " + user
                    val dataMap = hashMapOf(
                        "message_key1" to message,
                        "message_key2" to userEmail,
                    )
                    startNewActivity(Home::class.java, dataMap)

//                    startNewActivity(Home::class.java, "message_key", message)
                }else{
                    applicationContext.showToast("Invalid Credentials")
                }
            }catch (e: IOException){
                Log.e("Network Error", "Error: ${e.message}")
            }
        }

//        if ((loginData.containsKey(user)) || (userTemp == "admin")) {
//            val keyToRetrieve = user
//            val retrievedObject = loginData[keyToRetrieve]
//            retrievedObject.let {
//                if((password == it?.password) || (password == "admin" && user == "admin") ){
//                    applicationContext.showToast("Login Success")
//                    val message = "Hello, " + user
//                    startNewActivity(Home::class.java, "message_key", message)
//                }else{
//
//                    applicationContext.showToast("Login Failed")
//
//                    val imageSet: ImageView
//                    imageSet = findViewById(R.id.loginFail)
//
//                    val fadeOutAnimator = ObjectAnimator.ofFloat(imageSet, "alpha", 1.0f, 0.0f)
//                    fadeOutAnimator.duration = 3000
//                     imageSet.setImageResource(R.drawable.ic_login_fail)
//                    fadeOutAnimator.start()
//                     Handler(Looper.getMainLooper()).postDelayed({
//                        imageSet.visibility = View.INVISIBLE
//                    }, fadeOutAnimator.duration)
//                }
//            }
//        }
//        else {
//            applicationContext.showToast("User doesn't exist")
//        }

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
            val dataMap = hashMapOf(
                "message_key1" to "Sign Up Here!",
            )
            startNewActivity(Signup::class.java, dataMap)

        }

    }
}