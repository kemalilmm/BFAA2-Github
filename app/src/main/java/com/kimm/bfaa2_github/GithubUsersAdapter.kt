package com.kimm.bfaa2_github

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimm.bfaa2_github.databinding.ItemGithubUsersBinding
import com.kimm.bfaa2_github.fragments.HomeFragmentDirections
import com.kimm.bfaa2_github.models.GithubUsers

class GithubUsersAdapter:RecyclerView.Adapter<GithubUsersAdapter.GithubUsersViewHolder>() {
    private  val list = ArrayList<GithubUsers>()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<GithubUsers>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class  GithubUsersViewHolder(val binding:ItemGithubUsersBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user:GithubUsers){
            binding.root.transitionName=user.avatar_url
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(civAvatar)
                tvLogin.text=user.login
                detail.setOnClickListener { view->
                    val toDetailFragment= HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                    toDetailFragment.username = user.login
                    view.findNavController().navigate(toDetailFragment)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        val view = ItemGithubUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GithubUsersViewHolder((view))
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size


}