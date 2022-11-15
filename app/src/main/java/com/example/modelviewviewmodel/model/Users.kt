package com.example.modelviewviewmodel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int, val email: String, val first_name: String, val last_name: String, val avatar: String,
)