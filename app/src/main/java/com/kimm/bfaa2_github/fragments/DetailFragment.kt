package com.kimm.bfaa2_github.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kimm.bfaa2_github.R
import com.kimm.bfaa2_github.SectionpagerAdapter
import com.kimm.bfaa2_github.databinding.FragmentDetailBinding
import com.kimm.bfaa2_github.viewmodel.DetailUserViewModel

class DetailFragment : Fragment() {
    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
        const val EXTRA_USERNAME = "EXTRA_USERNAME"
    }
    private var _binding : FragmentDetailBinding?= null
    private val binding get()=_binding!!
    private lateinit var viewModel:DetailUserViewModel
    val bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataUsername = DetailFragmentArgs.fromBundle(arguments as Bundle).username
        bundle.putString(EXTRA_USERNAME,dataUsername)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(dataUsername)
        viewModel.getUserDetail().observe(viewLifecycleOwner,{
            if(it!=null){
                binding.apply {
                    val login = it.login
                    val username ="@"+it.login
                    tvName.text = it.name
                    tvUsername.text = username
                    tvDetailFollowersNumber.text = it.followers.toString()
                    tvDetailFollowingNumber.text = it.following.toString()
                    tvDetailReposNumber.text = it.public_repos.toString()
                    Glide.with(this@DetailFragment)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(civAvatar)
                    tvLocationName.text = it.location
                    tvCompanyName.text=it.company

                    shareButton.setOnClickListener {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "github.com/$login")
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)
                    }
                }
            }
        })
        val sectionpagerAdapter = SectionpagerAdapter(this,bundle)
        val viewPager:ViewPager2 = binding.vp2
        viewPager.adapter = sectionpagerAdapter
        val tabs :TabLayout=binding.tabs
        TabLayoutMediator(tabs,viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        return view
    }


}