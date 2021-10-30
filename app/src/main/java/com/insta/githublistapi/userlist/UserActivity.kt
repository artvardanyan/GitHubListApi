package com.insta.githublistapi.userlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.insta.githublistapi.clickListener.OnItemClickListener
import com.insta.githublistapi.model.UserResponse
import com.insta.githublistapi.databinding.ActivityUserBinding
import com.insta.githublistapi.profileuser.ProfileActivity

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel
    private val adapter: UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java
        )

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@UserActivity)
            rvUser.adapter = adapter
            btnSearch.setOnClickListener {
                searchUser()
            }
            errorText.visibility = View.GONE
        }

        viewModel.listUsers.observe(this, { users ->
            users?.let {
                adapter.updateData(it)
                binding.errorText.visibility = View.GONE
                showLoading(false)
            }
        })

        adapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(item: UserResponse) {
                Intent(this@UserActivity, ProfileActivity::class.java).also {
                    it.putExtra(ProfileActivity.EXTRA_USERNAME, item.login)
                    startActivity(it)
                }
            }
        }
    }

    private fun searchUser() {
        val query = binding.etQuery.text.toString()
        if (query.isEmpty()) return
        val isConnected = isNetworkConnect()
        binding.errorText.isVisible = !isConnected
        if (isConnected) {
            showLoading(true)
            viewModel.getSearchByUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun isNetworkConnect(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo = cm.activeNetworkInfo
        return netinfo != null && netinfo.isConnected
    }

}