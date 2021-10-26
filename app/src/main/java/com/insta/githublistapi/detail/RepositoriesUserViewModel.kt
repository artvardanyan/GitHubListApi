package com.insta.githublistapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insta.githublistapi.api.RetrofitClient
import com.insta.githublistapi.data.model.UserResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RepositoriesUserViewModel : ViewModel() {

    val user = MutableLiveData<UserResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.userService
            .getUserByName(username)
            .enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    user.postValue(response.body())
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.i("Failure", t.message!!)
                }
            })
    }
}