package com.example.megatrustchallenge.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.megatrustchallenge.R
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.databinding.FragmentFavouriteBinding
import com.example.megatrustchallenge.databinding.FragmentJobsFragmentsBinding
import com.example.megatrustchallenge.ui.adapter.JobAdapter
import com.example.megatrustchallenge.ui.adapter.favAdapter
import com.example.megatrustchallenge.ui.details.JobDetailsActivity
import com.example.megatrustchallenge.ui.jobFragment.JobsViewModel
import java.io.Serializable

class FavpuriteFragment : Fragment() {

    private lateinit var favViewModel: FavouriteViewModel
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var favAdapter: favAdapter
    private var jobs: List<JobsItem> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        favViewModel = ViewModelProvider(requireActivity()).get(FavouriteViewModel::class.java)

        val lay = LinearLayoutManager(activity)
        binding.jobsRecyclerView.layoutManager = lay
        favAdapter = favAdapter(favViewModel,jobs)
        binding.jobsRecyclerView.adapter = favAdapter


        favViewModel.getAllFavourires().observe(requireActivity()){
            favAdapter.favList = it
            favAdapter.notifyDataSetChanged()
        }


        favViewModel.itemClick.observe(requireActivity()){
            val intent = Intent(requireContext(), JobDetailsActivity::class.java)
            intent.putExtra("extra_object", it as Serializable)
            startActivity(intent)
        }


        favViewModel.favouriteClick.observe(requireActivity()){
            Log.d("TAG","favourite $it")
            favViewModel.deleteFomRoom(it.id)
        }


       return binding.root
    }

}