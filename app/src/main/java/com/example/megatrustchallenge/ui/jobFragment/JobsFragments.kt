package com.example.megatrustchallenge.ui.jobFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.databinding.FragmentJobsFragmentsBinding
import com.example.megatrustchallenge.ui.adapter.JobAdapter

class JobsFragments : Fragment() {
    private lateinit var jobsViewModel: JobsViewModel
    private lateinit var binding: FragmentJobsFragmentsBinding
    private lateinit var jobAdapter: JobAdapter
    private var jobs: List<JobsItem> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentJobsFragmentsBinding.inflate(inflater, container, false)

        jobsViewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[JobsViewModel::class.java]
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.jobsRecyclerView.layoutManager = lay
        jobAdapter = JobAdapter(jobs)
        binding.jobsRecyclerView.adapter = jobAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navController = Navigation.findNavController(requireActivity(),
//                R.id.fragmentHost)
//        val action = JobsFragmentsDirections.actionJobsFragments2ToDetailsFragment()
//        navController.navigate(R.id.action_jobsFragments2_to_detailsFragment)


        jobsViewModel.getJobsData(requireContext())
        jobsViewModel.offline.observe(requireActivity()) {
            if (it) {
                binding.animationOffLine.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

        jobsViewModel.progressBar.observe(requireActivity()) {
            if (it == false) {
                binding.progressBar.visibility = View.GONE
            }
        }

        jobsViewModel.jobsData.observe(requireActivity()){
            jobAdapter = JobAdapter(jobs)
            binding.jobsRecyclerView.adapter=jobAdapter
             jobAdapter.jobList = it
                jobAdapter.notifyDataSetChanged()
                Log.d("TAG",it.count().toString())
        }


    }

}