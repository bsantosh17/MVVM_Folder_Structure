package com.example.mvvm_folder_structure2.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_folder_structure2.R
import com.example.mvvm_folder_structure2.databinding.ActivityMainBinding
import com.example.mvvm_folder_structure2.ui.login.model.User
import com.example.mvvm_folder_structure2.ui.login.model.UserResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: LoginViewModel

    private  var TAG = "LoginCheck"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModel.loginMutableLiveData.observe(this, Observer<UserResponse?> {
           if(it?.token != null){
               Toast.makeText(this,"Login Successfully....",Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(this,"Incorrect Credentials",Toast.LENGTH_SHORT).show()
           }
        })


        binding.btn.setOnClickListener {
            val user = User(binding.editEmail.text.toString(),binding.editPassword.text.toString())
            viewModel.loginCallApi(user)
        }


    }
}