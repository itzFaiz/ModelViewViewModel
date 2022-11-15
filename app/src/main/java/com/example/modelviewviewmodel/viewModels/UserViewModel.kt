package com.example.modelviewviewmodel.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.modelviewviewmodel.model.Pages
import com.example.modelviewviewmodel.model.Users
import com.example.modelviewviewmodel.repository.UserRepository
import com.example.modelviewviewmodel.roomDatabase.UserDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel constructor(
    private val userRepository: UserRepository,

) : ViewModel() {
    private lateinit var userDatabase: UserDatabase
    val userList = MutableLiveData<List<Users>>()
    val errorMessage = MutableLiveData<String>()


    fun getAllUsers(page: Int) {
        var response = userRepository.getAllUsers(page)
        response.enqueue(object : Callback<Pages> {
            override fun onResponse(call: Call<Pages>, response: Response<Pages>) {
                if (response.body() != null){
                    userDatabase.userDao().insertUser(response.body()!!.data)
                }

                    userList.postValue(response.body()?.data)

            }

            override fun onFailure(call: Call<Pages>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })


    }
}