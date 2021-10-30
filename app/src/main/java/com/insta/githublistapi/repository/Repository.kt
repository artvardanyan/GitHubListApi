package com.insta.githublistapi.repository

import com.insta.githublistapi.model.UserListResponse
import com.insta.githublistapi.model.UserResponse
import com.insta.githublistapi.serviceapi.RetrofitClient


class Repository {

    suspend fun getProfileByUser(query: String): Result<UserResponse> {
        return try {
            val profile = RetrofitClient.userService.getUserByName(query)
            Result.Success(profile)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

    suspend fun getSearchByUser(query: String): Result<UserListResponse> {
        return try {
            val user = RetrofitClient.userService.searchUsers(query)
            Result.Success(user)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
}

sealed class Result<out T> {
    data class Success<out D>(val data: D) : Result<D>()
    data class Error(val t: Throwable) : Result<Nothing>()
}