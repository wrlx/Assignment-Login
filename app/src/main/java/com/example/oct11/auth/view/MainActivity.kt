package com.example.oct11.auth.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
//
//class MainActivity : AppCompatActivity() {
//    lateinit var userShared: String
//    lateinit var emailSharedLive: String
//    lateinit var firstNameSharedLive: String
//    lateinit var lastNameSharedLive: String
//    lateinit var userEmail: String
//
//
//    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, message, duration).show()
//    }
//
////    fun Context.startNewActivity(destination: Class<*>, key: String, value: String) {
////        val intent = Intent(this, destination)
////        intent.putExtra(key, value)
////        startActivity(intent)
////    }
//fun Context.startNewActivity(destination: Class<*>, dataMap: HashMap<String, String>) {
//    val intent = Intent(this, destination)
//    for ((key, value) in dataMap) {
//        intent.putExtra(key, value)
//    }
//    startActivity(intent)
//}
//
//
//    fun doLoginCheck(user : String, password : String){
//        var userTemp = user.toString()
//        var passwordTemp = password.toString()
//        var apiResponse: Response<UserAuth>? = null
//        Log.d("User : ", userTemp)
//        Log.d("password : ", passwordTemp)
//        val quoteApi = RetrifitHelperUserAuth.getInstance().create(UserFetchable::class.java)
//        GlobalScope.launch {
//            val userAuthData = UserData(userTemp, passwordTemp)
//            val result = quoteApi.postData(userAuthData)
//            apiResponse = result
//
//            result.body()?.let { user ->
//                userShared = user.username.toString()
//                emailSharedLive = user.email.toString()
//                firstNameSharedLive = user.firstName.toString()
//                lastNameSharedLive = user.lastName.toString()
//                userEmail = user.email.toString()
//            }
////            var userEmail = result.body()?.email.toString()
//            try{
//                if(result.isSuccessful || (password == "admin" && userTemp == "admin")) {
//                    Log.d("login : ", result.body().toString())
//                    val message = "Hello, " + user
//                    val dataMap = hashMapOf(
//                        "message_key1" to message,
//                        "message_key2" to userEmail,
//                    )
//                    storeToSharedPreference()
//                    startNewActivity(Home::class.java, dataMap)
//
//                }else{
//                    applicationContext.showToast("Invalid Credentials")
//                }
//            }catch (e: IOException){
//                Log.e("Network Error", "Error: ${e.message}")
//            }
//        }
//
////        if ((loginData.containsKey(user)) || (userTemp == "admin")) {
////            val keyToRetrieve = user
////            val retrievedObject = loginData[keyToRetrieve]
////            retrievedObject.let {
////                if((password == it?.password) || (password == "admin" && user == "admin") ){
////                    applicationContext.showToast("Login Success")
////                    val message = "Hello, " + user
////                    startNewActivity(Home::class.java, "message_key", message)
////                }else{
////
////                    applicationContext.showToast("Login Failed")
////
////                    val imageSet: ImageView
////                    imageSet = findViewById(R.id.loginFail)
////
////                    val fadeOutAnimator = ObjectAnimator.ofFloat(imageSet, "alpha", 1.0f, 0.0f)
////                    fadeOutAnimator.duration = 3000
////                     imageSet.setImageResource(R.drawable.ic_login_fail)
////                    fadeOutAnimator.start()
////                     Handler(Looper.getMainLooper()).postDelayed({
////                        imageSet.visibility = View.INVISIBLE
////                    }, fadeOutAnimator.duration)
////                }
////            }
////        }
////        else {
////            applicationContext.showToast("User doesn't exist")
////        }
//
//    }
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val etUsername: EditText
//        val etPassword: EditText
//        val btnLogin: Button
//        val btnSignup: TextView
//
//        etUsername = findViewById(R.id.userName)
//        etPassword = findViewById(R.id.password)
//        btnLogin = findViewById(R.id.loginButton)
//        btnSignup = findViewById(R.id.toSignup)
//
//
//        btnLogin.setOnClickListener {
//            val userName = etUsername.text.toString()
//            val password = etPassword.text.toString()
//            doLoginCheck(userName, password)
//
//        }
//        btnSignup.setOnClickListener {
//            val dataMap = hashMapOf(
//                "message_key1" to "Sign Up Here!"
//            )
//            startNewActivity(Signup::class.java, dataMap)
//
//        }
//
//    }
//
//    private fun storeToSharedPreference() {
//        val sharedPreferences = getSharedPreferences("LoginData", MODE_PRIVATE)
//        val myEdit = sharedPreferences.edit()
//
//        // write all the data entered by the user in SharedPreference and apply
//        myEdit.putString("uName", userShared.toString())
//        myEdit.putString("email", emailSharedLive.toString())
//        myEdit.putString("fName", firstNameSharedLive.toString())
//        myEdit.putString("lName", lastNameSharedLive.toString())
//        myEdit.apply()
//    }
//}

import androidx.lifecycle.ViewModelProvider
import com.example.oct11.home.Home
import com.example.oct11.R
import com.example.oct11.auth.AuthViewModelFactory
import com.example.oct11.signup.Signup
import com.example.oct11.auth.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel

    fun Context.startNewActivity(destination: Class<*>, dataMap: HashMap<String, String>) {
        val intent = Intent(this, destination)
        for ((key, value) in dataMap) {
            intent.putExtra(key, value)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        authViewModel = ViewModelProvider(this, AuthViewModelFactory(applicationContext)).get(AuthViewModel::class.java)
//        authViewModel = ViewModelProvider(this, AuthViewModelFactory(applicationContext)).get(AuthViewModel::class.java)
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(applicationContext)).get(AuthViewModel::class.java)



        val etUsername: EditText = findViewById(R.id.userName)
        val etPassword: EditText = findViewById(R.id.password)
        val btnLogin: Button = findViewById(R.id.loginButton)
        val btnSignup: TextView = findViewById(R.id.toSignup)

        btnLogin.setOnClickListener {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            authViewModel.doLoginCheck(userName, password)
        }

        btnSignup.setOnClickListener {
            val dataMap = hashMapOf(
                "message_key1" to "Sign Up Here!"
            )
            startNewActivity(Signup::class.java, dataMap)
        }

        // Observe LiveData properties and update UI as needed
        authViewModel.userSharedLive.observe(this) { user ->
            // Update the UI with the user data
        }

        authViewModel.emailSharedLive.observe(this) { email ->
            // Update the UI with the email data
        }

        authViewModel.firstNameSharedLive.observe(this) { firstName ->
            // Update the UI with the first name data
        }

        authViewModel.lastNameSharedLive.observe(this) { lastName ->
            // Update the UI with the last name data
        }
        authViewModel.navigateToHomeLiveData.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val dataMap = hashMapOf(
                    "message_key1" to "Hello, " + authViewModel.userSharedLive.value.toString(),
                    "message_key2" to authViewModel.emailSharedLive.value.toString()
                )
                startNewActivity(Home::class.java, dataMap)
            }
        })
    }
}
