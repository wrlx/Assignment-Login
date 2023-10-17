package com.example.oct11


import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiFetchable {
    @GET("/v2/quotes")
    suspend fun getData() : Response<List<String>>

}