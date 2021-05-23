package com.example.megatrustchallenge.ui.startActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.databinding.ActivityStartBinding
import com.example.megatrustchallenge.ui.jobScreen.JobsScreen

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.startButton.setOnClickListener {
            startActivity(Intent(this,JobsScreen::class.java))
        }
    }
}