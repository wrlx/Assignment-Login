package com.example.oct11.quote


import retrofit2.Response
import retrofit2.http.GET

interface ApiQuoteFetchable {
    @GET("/v2/quotes")
    suspend fun getData() : Response<List<String>>

}