package com.example.megatrustchallenge.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.ui.favourite.FavouriteViewModel

class favAdapter(var favouriteViewModel: FavouriteViewModel , favList: List<JobsItem>) : RecyclerView.Adapter<favAdapter.ViewHolder>() {
//    var jobsViewModel: JobsViewModel = jobsViewModel

    var favList: List<JobsItem> = ArrayList()
    set(value) {
        field = value
    }
    init {
        this.favList = favList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favAdapter.ViewHolder {
        val binding = LayoutInflater.from(parent.context).
        inflate(R.layout.job_item, parent, false)

        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(favList[position])
        holder.favourite.setOnClickListener {
            if (it.id == R.id.favourite){
                favouriteViewModel.favouriteClick.value = favList[position]
                Log.d("TAG","Favourite")
            }
        }
        holder.itemView.setOnClickListener {
           favouriteViewModel.itemClick.value = favList[position]
            Log.d("TAG", "Item")
        }
    }

    override fun getItemCount(): Int {
        return favList.count()
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo = itemView.findViewById<ImageView>(R.id.company_logo)
        val favourite = itemView.findViewById<ImageButton>(R.id.favourite)
        private val companyName = itemView.findViewById<TextView>(R.id.company_name)
        private val jobTitle = itemView.findViewById<TextView>(R.id.jobTitle)
        fun binding(jobsItem: JobsItem){
            Glide.with(companyLogo)
                    .load(jobsItem.company_logo?:"")
                    .fitCenter()
                    .placeholder(R.drawable.ic_loading)
                    .into(companyLogo)
            companyName.text = jobsItem.company?:"Company name is unknown"
            jobTitle.text = jobsItem.title?:"Job Title name is unknown"
            favourite.setBackgroundColor(Color.YELLOW)

        }
    }

}