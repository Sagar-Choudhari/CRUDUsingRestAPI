package com.example.crudusingrestapi.remote

import com.example.crudusingrestapi.remote.RetrofitClient.getClient

object APIUtils {
    const val API_URL = "http://169.254.35.189:8080/demo/"
    val userService: UserService
        get() = getClient(API_URL)!!.create(UserService::class.java)
}