package com.insta.githublistapi.api

import com.insta.githublistapi.data.model.UserResponse
import com.insta.githublistapi.data.model.UserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserListResponse>

    @GET("users/{username}")
    fun getUserByName(@Path("username") username: String): Call<UserResponse>

    @GET("user/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<UserResponse>>

    @GET("user/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<UserResponse>>
}