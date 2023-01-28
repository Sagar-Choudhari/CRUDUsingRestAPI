package com.example.crudusingrestapi.remote



import com.example.crudusingrestapi.model.User
import retrofit2.Call
import retrofit2.http.*


interface UserService {
    @get:GET("user/")
    val getUsers: Call<List<User?>?>?

    @POST("add/")
    fun addUser(@Body user: User?): Call<User?>?

    @PUT("update/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User?): Call<User?>?

    @DELETE("delete/{id}")
    fun deleteUser(@Path("id") id: Int): Call<User?>?
}