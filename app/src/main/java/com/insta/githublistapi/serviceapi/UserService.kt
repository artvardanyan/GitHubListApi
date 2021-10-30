package com.insta.githublistapi.serviceapi

import com.insta.githublistapi.model.UserResponse
import com.insta.githublistapi.model.UserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): UserListResponse

    @GET("users/{username}")
    suspend fun getUserByName(@Path("username") username: String): UserResponse

    @GET("user/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<UserResponse>>

    @GET("user/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<UserResponse>>
}