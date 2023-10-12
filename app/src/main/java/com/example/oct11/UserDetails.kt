package com.example.oct11

data class UserDetails(
    val id: String,
    val name: String,
    val email: String,
    val password: String
)
val loginData = mutableMapOf<String , UserDetails>()
