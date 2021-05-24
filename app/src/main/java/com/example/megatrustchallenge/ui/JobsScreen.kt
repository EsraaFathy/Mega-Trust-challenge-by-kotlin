package com.example.megatrustchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.databinding.ActivityJobsScreenBinding
import com.example.megatrustchallenge.ui.favourite.FavouriteFragment
import com.example.megatrustchallenge.ui.jobFragment.JobsFragments

class JobsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityJobsScreenBinding
    private lateinit var fragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jobs_screen)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem_menu -> {
                    fragment = JobsFragments()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHost, fragment)
                            .commit()
                }
                else -> {
                    fragment = FavouriteFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHost, fragment)
                            .commit()
                }
            }
            return@setOnNavigationItemSelectedListener true

        }
    }
}