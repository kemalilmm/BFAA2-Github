package com.kimm.bfaa2_github.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimm.bfaa2_github.api.RetrofitClient
import com.kimm.bfaa2_github.api.UsersResponse
import com.kimm.bfaa2_github.models.GithubUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val listUsers = MutableLiveData<ArrayList<GithubUsers>>()
    val error = MutableLiveData<String>()
    fun setSearchUsers(query:String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UsersResponse>{
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable,) {
                    Log.d("Failure", t.message.toString())
                    error.postValue(t.message.toString())
                }

            })
    }
    fun getSearchUsers():LiveData<ArrayList<GithubUsers>>{
        return listUsers
    }
}