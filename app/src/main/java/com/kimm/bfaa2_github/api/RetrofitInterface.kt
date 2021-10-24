package com.kimm.bfaa2_github.api

import com.kimm.bfaa2_github.models.GithubUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q")query:String?
    ):Call<UsersResponse>

    @GET("users/{username}")
     fun userDetail(
        @Path("username") username: String?
    ): Call<GithubUsers>

    @GET("users/{username}/followers")
     fun userFollower(
        @Path("username") username: String
    ): Call<ArrayList<GithubUsers>>

    @GET("users/{username}/following")
     fun userFollowing(
        @Path("username") username: String
    ): Call<ArrayList<GithubUsers>>
}