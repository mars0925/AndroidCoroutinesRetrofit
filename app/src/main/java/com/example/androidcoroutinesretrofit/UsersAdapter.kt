package com.example.androidcoroutinesretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoroutinesretrofit.databinding.ItemBinding
import com.example.androidcoroutinesretrofit.extension.loadImage
import com.example.androidcoroutinesretrofit.model.User

class UsersAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    fun updateCountries(newCountries: List<User>) {
        users.clear()
        users.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UserViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val userImage = binding.imageView
        private val userPhone = binding.userPhone
        private val userName = binding.userName
        private val userMail = binding.userMail

        fun bind(user: User) {
            userName.text = "${user.name.first} ${user.name.last}"
            userPhone.text = user.phone
            userMail.text = user.email
            userImage.loadImage(user.picture.medium)
        }
    }
}