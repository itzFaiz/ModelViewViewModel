package com.example.modelviewviewmodel.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modelviewviewmodel.model.Users

@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    private var INSTANCE : UserDatabase? = null
    fun getDatabase(context: Context): UserDatabase{
        if(INSTANCE == null){
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context,UserDatabase::class.java,
                    "user_database"
                )
                    .build()
            }
        }
        return INSTANCE!!
    }
}