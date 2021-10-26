package com.insta.githublistapi.clickListener

import com.insta.githublistapi.model.UserResponse

interface OnItemClickListener {
    fun onItemClick(item: UserResponse)
}