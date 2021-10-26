package com.insta.githublistapi.data.model

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("items")
    val items: List<UserResponse>
)