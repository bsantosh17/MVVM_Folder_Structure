package com.example.mvvm_folder_structure2.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_folder_structure2.data.ApiService
import com.example.mvvm_folder_structure2.data.RetrofitInstance
import com.example.mvvm_folder_structure2.domain.UseCase.Companion.getListUrl
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListViewModel : ViewModel(){

    val getUserListLiveData:MutableLiveData<UserListResponse?> = MutableLiveData()
    val userLiveData:LiveData<UserListResponse?>
        get() = getUserListLiveData

    fun getUserListObserver():LiveData<UserListResponse?>{
        return userLiveData
    }

    fun getAllUserList(){
        val retro = RetrofitInstance(getListUrl).getRetrofitInstance().create(ApiService::class.java)
        retro.getUserList().enqueue(object : Callback<UserListResponse>{
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                if(response.isSuccessful){
                    getUserListLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                getUserListLiveData.postValue(null)
            }

        })

    }
}