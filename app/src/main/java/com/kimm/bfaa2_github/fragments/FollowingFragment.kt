package com.kimm.bfaa2_github.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kimm.bfaa2_github.FollowDetailAdapter
import com.kimm.bfaa2_github.R
import com.kimm.bfaa2_github.databinding.FragmentFollowBinding
import com.kimm.bfaa2_github.viewmodel.FollowingViewModel

class FollowingFragment: Fragment(R.layout.fragment_follow) {
    private var _binding:FragmentFollowBinding? =null
    private val binding get() = _binding!!
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: FollowDetailAdapter
    private lateinit var username:String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(DetailFragment.EXTRA_USERNAME).toString()
        _binding= FragmentFollowBinding.bind(view)
        adapter = FollowDetailAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            rvFollowusers.setHasFixedSize(true)
            rvFollowusers.layoutManager = LinearLayoutManager(activity)
            rvFollowusers.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)
        viewModel.setFollowingList(username)
        viewModel.getFollowingList().observe(viewLifecycleOwner,{
            if(it!=null){
                showLoading(false)
                adapter.setList(it)
            }
        })
        viewModel.error.observe(viewLifecycleOwner,{
            Snackbar.make(binding.followFragment,it, Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.colorPrimary))
                .show()
            showLoading(false)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}