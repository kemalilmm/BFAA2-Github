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

class FollowersViewModel: ViewModel() {
    val followersList = MutableLiveData<ArrayList<GithubUsers>>()
    val error = MutableLiveData<String>()
    fun setFollowersList(username:String){
        RetrofitClient.apiInstance
            .userFollower(username)
            .enqueue(object :Callback<ArrayList<GithubUsers>>{
                override fun onResponse(
                    call: Call<ArrayList<GithubUsers>>,
                    response: Response<ArrayList<GithubUsers>>
                ) {
                    if(response.isSuccessful){
                        followersList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUsers>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    error.postValue(t.message.toString())
                }

            })
    }
    fun getFollowersList(): LiveData<ArrayList<GithubUsers>> {
        return followersList
    }
}