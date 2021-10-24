package com.kimm.bfaa2_github.api

import android.os.Parcelable
import com.kimm.bfaa2_github.models.GithubUsers
import kotlinx.parcelize.Parcelize


@Parcelize
data class UsersResponse(
    val items : ArrayList<GithubUsers>
):Parcelable
