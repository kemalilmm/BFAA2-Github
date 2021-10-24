package com.kimm.bfaa2_github.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubUsers(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val name: String?,
    val location: String?,
    val type: String?,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val company: String?
):Parcelable
