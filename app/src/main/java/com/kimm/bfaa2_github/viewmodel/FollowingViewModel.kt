package com.kimm.bfaa2_github.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimm.bfaa2_github.api.RetrofitClient
import com.kimm.bfaa2_github.models.GithubUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val followingList = MutableLiveData<ArrayList<GithubUsers>>()
    val error = MutableLiveData<String>()
    fun setFollowingList(username:String){
        RetrofitClient.apiInstance
            .userFollowing(username)
            .enqueue(object : Callback<ArrayList<GithubUsers>> {
                override fun onResponse(
                    call: Call<ArrayList<GithubUsers>>,
                    response: Response<ArrayList<GithubUsers>>
                ) {
                    if(response.isSuccessful){
                        followingList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUsers>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    error.postValue(t.message.toString())
                }

            })
    }
    fun getFollowingList(): LiveData<ArrayList<GithubUsers>> {
        return followingList
    }
}