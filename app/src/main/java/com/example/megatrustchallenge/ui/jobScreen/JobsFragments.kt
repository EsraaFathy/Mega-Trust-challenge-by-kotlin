package com.example.megatrustchallenge.ui.jobScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.megatrustchallenge.R

class JobsFragments : Fragment(R.layout.fragment_jobs_fragments) {
    private lateinit var jobsViewModel: JobsViewModel

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        val view =inflater.inflate(R.layout.fragment_jobs_fragments, container, false)
//
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navController = Navigation.findNavController(requireActivity(),
//                R.id.fragmentHost)
//        val action = JobsFragmentsDirections.actionJobsFragments2ToDetailsFragment()
//        navController.navigate(R.id.action_jobsFragments2_to_detailsFragment)
        jobsViewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[JobsViewModel::class.java]
        jobsViewModel.getJobsData()


    }

}