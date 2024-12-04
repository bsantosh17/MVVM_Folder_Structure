package com.example.mvvm_folder_structure2.ui.dashboard

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_folder_structure2.R
import com.example.mvvm_folder_structure2.databinding.ActivityDashboardBinding
import com.example.mvvm_folder_structure2.sharePreference.SharePrefManager
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponse
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponseItem

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    private lateinit var viewModel: UserListViewModel

    lateinit var userAdapter: UserAdapter

    private lateinit var arrayList: ArrayList<UserListResponseItem>
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            val sharePrefManager = SharePrefManager(this)
            sharePrefManager.clear()
        }

        arrayList = arrayListOf()
        initRecyclerView()

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        viewModel.getAllUserList()

        viewModel.getUserListObserver().observe(this, Observer<UserListResponse?> {
          if (it == null){
              Toast.makeText(this,"No Records",Toast.LENGTH_SHORT).show()
          }else{

              for(i in it){
                  val id = i.id
                  val title = i.title
                  val url = i.thumbnailUrl

                  val list = UserListResponseItem("",id,url,title,"")
                  arrayList.add(list)
              }
              userAdapter.notifyDataSetChanged()

          }
        })



    }

    fun initRecyclerView(){
        binding.recyclerView.apply {
            val layoutManager = LinearLayoutManager(this@DashboardActivity)
            binding.recyclerView.layoutManager = layoutManager
            val decoration = DividerItemDecoration(this@DashboardActivity,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            userAdapter = UserAdapter(this@DashboardActivity,arrayList)
            adapter = userAdapter


        }
    }
}