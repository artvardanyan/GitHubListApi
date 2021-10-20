package com.insta.githublistapi

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.insta.githublistapi.data.model.User
import com.insta.githublistapi.databinding.ActivityMainBinding
import com.insta.githublistapi.detail.Repositories

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var viewModel: MainViewModel? = null
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )

        viewModel?.getSearchUser()?.observe(this, {
            if (it != null) {
                adapter?.setList(it)
                showLoading(false)
            }
        })

        adapter = UserAdapter()
        adapter?.notifyDataSetChanged()

        adapter?.setOnItemClickListener(object : UserAdapter.OnItemListener {
            override fun onItemClick(data: User) {
                Intent(this@MainActivity, Repositories::class.java).also {
                    it.putExtra(Repositories.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        binding?.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
            btnSearch.setOnClickListener {
                searchUser()
            }
            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun searchUser() {
        val query = binding?.etQuery?.text.toString()
        if (query.isEmpty()) return
        showLoading(true)
        viewModel?.setSearchUsers(query)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}