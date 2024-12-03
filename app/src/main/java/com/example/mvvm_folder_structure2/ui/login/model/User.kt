package com.example.mvvm_folder_structure2.ui.login.model

data class User(val email:String, val password:String)

data class UserResponse(
    val token: String,
    val username: String
)