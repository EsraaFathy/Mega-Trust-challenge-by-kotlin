package com.example.megatrustchallenge.ui.details

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.databinding.ActivityJobDetailsBinding

class JobDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_details)

        val jobsItem = intent.extras?.get("extra_object") as JobsItem





        Glide.with(binding.companyLogo)
                .load(jobsItem.company_logo?:"")
                .fitCenter()
                .placeholder(R.drawable.ic_loading)
                .into(binding.companyLogo)

        binding.jobTitle.text = jobsItem.title?:getString(R.string.not_found)
        binding.companyName.text = jobsItem.company?:getString(R.string.not_found)
        binding.jobType.text = jobsItem.type?:getString(R.string.not_found)
        binding.jobURL.text = jobsItem.url?:getString(R.string.not_found)
        binding.jobURL.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(jobsItem.url?:getString(R.string.not_found)))
            startActivity(browserIntent)
        }

        binding.companyURL.text = jobsItem.company_url?:getString(R.string.not_found)
        binding.jobURL.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(jobsItem.company_url?:getString(R.string.not_found)))
            startActivity(browserIntent)
        }

        binding.jobDescription.text = jobsItem.description?:getString(R.string.not_found)

    }
}