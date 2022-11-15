package com.example.modelviewviewmodel.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modelviewviewmodel.repository.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory constructor(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(this.userRepository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}