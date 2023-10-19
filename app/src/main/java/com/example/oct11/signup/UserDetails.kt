package com.example.oct11.signup

data class UserDetails(
    val id: String,
    val name: String,
    val email: String,
    val password: String
)
val loginData = mutableMapOf<String , UserDetails>()
