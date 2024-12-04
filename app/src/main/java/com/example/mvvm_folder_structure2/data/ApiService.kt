package com.example.mvvm_folder_structure2.data

import android.telecom.Call
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponse
import com.example.mvvm_folder_structure2.ui.login.model.User
import com.example.mvvm_folder_structure2.ui.login.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun userLogin(
        @Body info:User
    ): retrofit2.Call<UserResponse>

    @GET("photos")
    fun getUserList():retrofit2.Call<UserListResponse>
}
