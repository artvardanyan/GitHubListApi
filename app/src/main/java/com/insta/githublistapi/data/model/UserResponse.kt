package com.insta.githublistapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("items")
    val items: ArrayList<User>
) : Parcelable