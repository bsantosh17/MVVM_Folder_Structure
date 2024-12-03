package com.example.mvvm_folder_structure2.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_folder_structure2.data.ApiService
import com.example.mvvm_folder_structure2.data.RetrofitInstance
import com.example.mvvm_folder_structure2.domain.UseCase.Companion.loginUrl
import com.example.mvvm_folder_structure2.ui.login.model.User
import com.example.mvvm_folder_structure2.ui.login.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel :ViewModel() {

    val loginMutableLiveData:MutableLiveData<UserResponse?> = MutableLiveData()

    fun getLoginData():MutableLiveData<UserResponse?>{
        return loginMutableLiveData
    }


    fun loginCallApi(user:User){
        val retro = RetrofitInstance(loginUrl).getRetrofitInstance().create(ApiService::class.java)

        retro.userLogin(user).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    loginMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    loginMutableLiveData.postValue(null)
            }

        })
    }
}