package com.example.megatrustchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import org.intellij.lang.annotations.JdkConstants

class JobAdapter(var jobList: List<JobsItem>) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapter.ViewHolder {
        val binding = LayoutInflater.from(parent.context).
        inflate(R.layout.job_item, parent, false)

        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: JobAdapter.ViewHolder, position: Int) {
        holder.binding(jobList[position],position)
    }

    override fun getItemCount(): Int {
        return jobList.count()
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo = itemView.findViewById<ImageView>(R.id.company_logo)
        private val companyName = itemView.findViewById<TextView>(R.id.company_name)
        private val jobTitle = itemView.findViewById<TextView>(R.id.jobTitle)
        fun binding(jobsItem: JobsItem, position: Int){
            Glide.with(companyLogo)
                    .load(jobsItem.company_logo)
                    .fitCenter()
                    .placeholder(R.drawable.ic_loading)
                    .into(companyLogo)
            companyName.text = jobsItem.company
            jobTitle.text = jobsItem.title
        }
    }

}