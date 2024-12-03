package com.example.mvvm_folder_structure2.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance(val url:String) {


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
                level =HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitInstance():Retrofit{

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}