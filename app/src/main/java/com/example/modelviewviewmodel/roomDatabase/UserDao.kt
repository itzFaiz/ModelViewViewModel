package com.example.modelviewviewmodel.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.modelviewviewmodel.model.Pages
import com.example.modelviewviewmodel.model.Users

@Dao
interface UserDao {

    @Query("SELECT * from users")
    fun getUsers(): LiveData<List<Pages>>

    @Insert
     fun insertUser(users: List<Users>)
}