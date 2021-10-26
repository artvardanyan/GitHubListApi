package com.insta.githublistapi

import com.insta.githublistapi.data.model.UserResponse

interface OnItemClickListener {
    fun onItemClick(item: UserResponse)
}