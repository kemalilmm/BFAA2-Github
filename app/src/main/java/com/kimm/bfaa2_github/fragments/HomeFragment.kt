package com.kimm.bfaa2_github.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kimm.bfaa2_github.GithubUsersAdapter
import com.kimm.bfaa2_github.R
import com.kimm.bfaa2_github.databinding.FragmentHomeBinding
import com.kimm.bfaa2_github.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding ?= null
    private val binding get()=_binding!!
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : GithubUsersAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = GithubUsersAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        searchUser()
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(view.context)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter=adapter
        }
        viewModel.getSearchUsers().observe(viewLifecycleOwner,{
            if(it!=null){
                showLoading(false)
                adapter.setList(it)
            }
        })
        viewModel.error.observe(viewLifecycleOwner,{
            Snackbar.make(binding.homeLayout,it,Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.colorPrimary))
                .show()
            showLoading(false)
        })
        return view
    }

    private fun searchUser() { binding.svQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            if (query.isEmpty()) {
                showLoading(false)
                return true
            } else {
                showLoading(true)
                viewModel.setSearchUsers(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            return false
        }
    })
    }
    private fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}