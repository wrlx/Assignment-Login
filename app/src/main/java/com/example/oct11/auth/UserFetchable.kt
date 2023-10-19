package com.example.oct11.auth

import com.example.oct11.auth.model.UserAuth
import com.example.oct11.auth.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserFetchable {
    @POST("/auth/login")
    suspend fun postData(@Body userData: UserData): Response<UserAuth>
}