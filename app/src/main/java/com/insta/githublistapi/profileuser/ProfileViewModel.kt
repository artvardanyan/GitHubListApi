package com.insta.githublistapi.profileuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insta.githublistapi.serviceapi.RetrofitClient
import com.insta.githublistapi.model.UserResponse
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val _profileUser = MutableLiveData<UserResponse>()
    val profileUser: LiveData<UserResponse>
        get() = _profileUser

    fun setUserDetail(username: String) {
        RetrofitClient.userService
            .getUserByName(username)
            .enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    _profileUser.postValue(response.body())
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.i("Failure", t.message!!)
                }
            })
    }
}