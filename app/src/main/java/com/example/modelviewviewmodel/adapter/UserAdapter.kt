package com.example.modelviewviewmodel.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modelviewviewmodel.databinding.AdapterUserBinding
import com.example.modelviewviewmodel.model.Users

class UserAdapter : RecyclerView.Adapter<UserAdapter.MainViewHolder>() {

    private var users = mutableListOf<Users>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(user : List<Users>){
        users.addAll(user)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUserBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val users = users[position]
        holder.binding.tvFirstname.text = users.first_name
        holder.binding.tvLastName.text = users.last_name
        holder.binding.tvEmail.text = users.email
        Glide.with(holder.itemView.context)
            .load(users.avatar)
            .into(holder.binding.ivProfile)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class MainViewHolder(val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}