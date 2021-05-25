package com.example.megatrustchallenge.ui.jobFragment

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
import com.example.megatrustchallenge.databinding.FragmentJobsFragmentsBinding
import com.example.megatrustchallenge.ui.adapter.JobAdapter
import com.example.megatrustchallenge.ui.details.JobDetailsActivity
import java.io.Serializable

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

        val lay = LinearLayoutManager(activity)

        binding.jobsRecyclerView.layoutManager = lay
        jobAdapter = JobAdapter(jobsViewModel,jobs)
        binding.jobsRecyclerView.adapter = jobAdapter



        binding.swapToRefresh.setColorSchemeResources(R.color.orange);

        binding.swapToRefresh.setOnRefreshListener {
            Log.d("Tag","Swaped")
            jobsViewModel.progressBar.value = true
            jobsViewModel.getJobsData(requireContext())
            binding.swapToRefresh.isRefreshing = false
        }

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
            }else{
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        jobsViewModel.jobsData.observe(requireActivity()){
             jobAdapter.jobList = it
                jobAdapter.notifyDataSetChanged()
                Log.d("TAG",it.count().toString())
        }


        jobsViewModel.itemClick.observe(requireActivity()){
            val intent = Intent(activity, JobDetailsActivity::class.java)
            intent.putExtra("extra_object", it as Serializable)
            startActivity(intent)
        }


        jobsViewModel.favouriteClick.observe(requireActivity()){
            Log.d("TAG","favourite $it")

        }

    }

}