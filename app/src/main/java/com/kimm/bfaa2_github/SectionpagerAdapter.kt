package com.kimm.bfaa2_github
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kimm.bfaa2_github.fragments.FollowersFragment
import com.kimm.bfaa2_github.fragments.FollowingFragment

class SectionpagerAdapter(fragment: Fragment, data:Bundle) :FragmentStateAdapter(fragment){
    private var fragmentBundle:Bundle
    init {
        fragmentBundle = data
    }
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowersFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }


}