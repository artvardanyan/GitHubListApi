package com.insta.githublistapi.profileuser

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insta.githublistapi.model.UserResponse
import com.insta.githublistapi.repository.Repository
import com.insta.githublistapi.repository.Result
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val profileRepository: Repository = Repository()

    private val _profileUserLiveData = MutableLiveData<UserResponse>()
    val profileUserLiveData: LiveData<UserResponse>
        get() = _profileUserLiveData


    @SuppressLint("NullSafeMutableLiveData")
    fun getProfileByUser(username: String) {

        viewModelScope.launch {

            val result = profileRepository.getProfileByUser(username)
            when (result) {
                is Result.Success -> {
                    _profileUserLiveData.value = result.data
                }
                is Result.Error -> TODO()
            }
        }
    }
}