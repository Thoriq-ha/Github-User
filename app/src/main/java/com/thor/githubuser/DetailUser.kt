package com.thor.githubuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thor.githubuser.databinding.ActivityDetailUserBinding
import com.thor.githubuser.models.User


class DetailUser : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        setUser(user)
        supportActionBar?.title = "Hai, this is ${user.name}"

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Halo, ini adalah :\n\n $user")
                type = "text/plain"
            }

            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }
    }

    private fun setUser(user: User) {
        val name = "Name        : " + user.name
        val location = "Location    : " + user.location
        val company = "Company    : " + user.company

        binding.circleImageView.setImageResource(user.avatar ?: 0)
        binding.tvUsername.text = user.username
        binding.tvFollowers.text = user.follower
        binding.tvFollowing.text = user.follower
        binding.tvRepository.text = user.repository
        binding.tvName.text = name
        binding.tvLocation.text = location
        binding.tvCompanyDetail.text = company
    }
}