package com.kimm.bfaa2_github

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimm.bfaa2_github.databinding.ItemFollowDetailBinding
import com.kimm.bfaa2_github.models.GithubUsers

class FollowDetailAdapter : RecyclerView.Adapter<FollowDetailAdapter.FollowDetailViewHolder>() {
    private  val list = ArrayList<GithubUsers>()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<GithubUsers>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class  FollowDetailViewHolder(val binding: ItemFollowDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: GithubUsers){
            binding.root.transitionName=user.avatar_url
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(civAvatar)
                tvLogin.text=user.login
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowDetailViewHolder {
        val view = ItemFollowDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowDetailViewHolder((view))
    }

    override fun onBindViewHolder(holder: FollowDetailViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size


}