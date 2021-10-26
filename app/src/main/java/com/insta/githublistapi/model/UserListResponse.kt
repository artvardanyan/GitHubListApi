package com.insta.githublistapi.model

import com.google.gson.annotations.SerializedName
import com.insta.githublistapi.model.UserResponse

data class UserListResponse(
    @SerializedName("items")
    val items: List<UserResponse>
)