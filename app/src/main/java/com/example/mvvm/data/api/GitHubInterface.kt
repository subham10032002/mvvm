package com.example.mvvm.data.api


import com.example.mvvm.data.models.User
import com.example.mvvm.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubInterface {

    @GET("users")
    suspend fun getUsers() : Response<List<User>>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")query:String) : Response<UserResponse>

}
