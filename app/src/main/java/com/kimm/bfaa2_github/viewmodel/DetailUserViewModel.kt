package com.kimm.bfaa2_github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimm.bfaa2_github.api.RetrofitClient
import com.kimm.bfaa2_github.models.GithubUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {
    val user = MutableLiveData<GithubUsers>()

    fun setUserDetail(username: String){
        RetrofitClient.apiInstance
            .userDetail(username)
            .enqueue(object :Callback<GithubUsers>{
                override fun onResponse(call: Call<GithubUsers>, response: Response<GithubUsers>) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<GithubUsers>, t: Throwable) {

                }

            })
    }
    fun getUserDetail():LiveData<GithubUsers>{
        return user
    }
}