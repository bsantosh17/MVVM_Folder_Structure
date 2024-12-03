package com.example.mvvm_folder_structure2.sharePreference

import android.content.Context
import android.content.SharedPreferences

class SharePrefManager(context:Context) {

    private val sharedPreferences:SharedPreferences = context.getSharedPreferences("loginCheck",Context.MODE_PRIVATE)


    fun saveLogin(name:String){
        sharedPreferences.edit().putString("Login",name).apply()
    }

    fun getLogin(): String?{
        return  sharedPreferences.getString("Login",null)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}