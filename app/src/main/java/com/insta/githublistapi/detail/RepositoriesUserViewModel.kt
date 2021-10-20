package com.insta.githublistapi.detail

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insta.githublistapi.api.RetrofitClient
import com.insta.githublistapi.data.model.DetailUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RepositoriesUserViewModel : ViewModel() {

    val user = MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : retrofit2.Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    viewModelScope.launch {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.i("Failure", t.message!!)
                }
            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }
}