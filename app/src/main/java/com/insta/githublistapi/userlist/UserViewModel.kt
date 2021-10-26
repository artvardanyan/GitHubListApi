package com.insta.githublistapi.userlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insta.githublistapi.serviceapi.RetrofitClient
import com.insta.githublistapi.model.UserListResponse
import com.insta.githublistapi.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _listUsers = MutableLiveData<List<UserResponse>>()
    val listUsers: LiveData<List<UserResponse>> = _listUsers

    fun searchUsers(query: String) {
        RetrofitClient.userService
            .searchUsers(query)
            .enqueue(object : Callback<UserListResponse> {
                override fun onResponse(
                    call: Call<UserListResponse>,
                    response: Response<UserListResponse>
                ) {
                    if (response.isSuccessful) {
                        _listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                    Log.i("Failure", t.message!!)
                }
            })
        }
}