package com.insta.githublistapi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.insta.githublistapi.databinding.ActivityRepositoriesBinding

class Repositories : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private var binding: ActivityRepositoriesBinding? = null
    private var viewModel : RepositoriesUserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            RepositoriesUserViewModel::class.java
        )

        if (username != null) {
            viewModel?.setUserDetail(username)
        }

        viewModel?.getUserDetail()?.observe(this, {
            if (it != null) {
                binding?.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@Repositories)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)

        binding?.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }
}