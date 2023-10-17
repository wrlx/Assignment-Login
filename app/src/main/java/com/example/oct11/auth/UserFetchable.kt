package com.example.oct11.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserFetchable {
    @POST("/auth/login")
    suspend fun postData(@Body userData: UserData): Response<UserAuth>
}