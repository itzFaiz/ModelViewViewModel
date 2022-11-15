package com.example.modelviewviewmodel.api


import com.example.modelviewviewmodel.constants.Constants
import com.example.modelviewviewmodel.model.Pages
import com.example.modelviewviewmodel.model.Users
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRetrofitService {

    @GET("users")
    fun getAllUsers(@Query("page")page : Int):  Call<Pages>

    companion object{
        var userRetrofitService: UserRetrofitService? = null

        fun getInstance(): UserRetrofitService{
            if (userRetrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                userRetrofitService = retrofit.create(UserRetrofitService::class.java)
            }
            return userRetrofitService!!
        }
    }
}