package com.example.oct11.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrifitHelperUserAuth {
    val baseUrl = "https://dummyjson.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}