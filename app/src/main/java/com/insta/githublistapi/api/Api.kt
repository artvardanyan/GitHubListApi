package com.insta.githublistapi.api

import com.insta.githublistapi.data.model.DetailUserResponse
import com.insta.githublistapi.data.model.User
import com.insta.githublistapi.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    fun getSearchUsers (@Query("q") query: String) : Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<DetailUserResponse>

    @GET("user/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<User>>

    @GET("user/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<User>>
}