package com.example.mvvm_folder_structure2.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_folder_structure2.databinding.UserItemBinding
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponse
import com.example.mvvm_folder_structure2.ui.dashboard.model.UserListResponseItem

class UserAdapter(val context: Context, private val userData:List<UserListResponseItem>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


        inner class UserViewHolder(val binding: UserItemBinding) :RecyclerView.ViewHolder(binding.root){

            fun bind(user: UserListResponseItem){
                binding.tvId.text = user.id.toString()
                binding.tvTitle.text = user.title.toString()
                Glide.with(context)
                    .load(user.url)
                    .into(binding.imageView)
            }

       }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userData.size   }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userData[position])
    }
}