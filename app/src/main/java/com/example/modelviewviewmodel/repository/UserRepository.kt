package com.example.modelviewviewmodel.repository

import com.example.modelviewviewmodel.api.UserRetrofitService
import com.example.modelviewviewmodel.roomDatabase.UserDatabase

class UserRepository constructor(private val userRetrofitService: UserRetrofitService) {

    fun getAllUsers(page : Int) = userRetrofitService.getAllUsers(page)
}