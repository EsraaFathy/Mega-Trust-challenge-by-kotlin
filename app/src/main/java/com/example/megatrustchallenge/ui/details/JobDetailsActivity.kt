package com.example.megatrustchallenge.ui.details

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.databinding.ActivityJobDetailsBinding
import com.example.megatrustchallenge.ui.jobFragment.JobsViewModel

class JobDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private var color : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_details)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val jobsItem = intent.extras?.get("extra_object") as JobsItem

        detailsViewModel.getFavourite(jobsItem.id).observe(this){
            if (it!=null && it.id==jobsItem.id) {
                binding.favourite.setBackgroundColor(Color.YELLOW)
                color = true
            }
        }
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

        binding.favourite.setOnClickListener {
            if (color) {
                detailsViewModel.deleteFomRoom(jobsItem.id)
                binding.favourite.setBackgroundColor(Color.BLACK)
            }
            else
                detailsViewModel.saveItem(jobsItem)
        }

    }
}