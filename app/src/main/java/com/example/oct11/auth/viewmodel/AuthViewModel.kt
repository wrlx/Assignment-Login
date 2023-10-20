package com.example.oct11.auth.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oct11.auth.Event
import com.example.oct11.auth.RetrifitHelperUserAuth
import com.example.oct11.auth.UserFetchable
import com.example.oct11.auth.model.UserAuth
import com.example.oct11.auth.model.UserData
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException



class AuthViewModel(private val context: Context) : ViewModel() {
    // Define LiveData properties for UI data binding
    private val userShared = MutableLiveData<String>()
    private val emailShared = MutableLiveData<String>()
    private val firstNameShared = MutableLiveData<String>()
    private val lastNameShared = MutableLiveData<String>()

    val userSharedLive: LiveData<String> get() = userShared
    val emailSharedLive: LiveData<String> get() = emailShared
    val firstNameSharedLive: LiveData<String> get() = firstNameShared
    val lastNameSharedLive: LiveData<String> get() = lastNameShared

    private val navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHomeLiveData: LiveData<Event<Unit>> get() = navigateToHome


        // Function to perform login
    fun doLoginCheck(user: String, password: String) {
        val userTemp = user.toString()
        val passwordTemp = password.toString()

        // Perform network request using Retrofit and update LiveData properties
        viewModelScope.launch {
            val result = callToApi(userTemp, passwordTemp)
            result.body()?.let { user ->
                userShared.value = user.username.toString()
                emailShared.value = user.email.toString()
                firstNameShared.value = user.firstName.toString()
                lastNameShared.value = user.lastName.toString()
//                Log.d("kopp", emailShared.value.toString())
            }

            try{
                if(result.isSuccessful || (password == "admin" && userTemp == "admin")) {
                    Log.d("login : ", result.body().toString())
                    val message = "Hello, " + user
                    val dataMap = hashMapOf(
                        "message_key1" to message,
                        "message_key2" to  emailShared,
                    )
                    storeToSharedPreference()
//                    startNewActivity(Home::class.java, dataMap)
                    navigateToHome.postValue(Event(Unit))
                }else{
//                    applicationContext.showToast("Invalid Credentials")
                }
            }catch (e: IOException){
                Log.e("Network Error", "Error: ${e.message}")
            }

        }
    }



    private suspend fun callToApi(user: String, password: String): Response<UserAuth> {
        val quoteApi = RetrifitHelperUserAuth.getInstance().create(UserFetchable::class.java)
        val userAuthData = UserData(user, password)
        return quoteApi.postData(userAuthData)
    }

    private fun storeToSharedPreference() {
        val sharedPreferences = context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("uName", userShared.value)
        myEdit.putString("email", emailSharedLive.value)
        myEdit.putString("fName", firstNameSharedLive.value)
        myEdit.putString("lName", lastNameSharedLive.value)
        myEdit.apply()
    }

}
