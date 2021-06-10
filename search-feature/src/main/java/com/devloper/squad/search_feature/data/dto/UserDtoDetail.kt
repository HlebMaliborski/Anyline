package com.devloper.squad.search_feature.data.dto

import com.google.gson.annotations.SerializedName

data class UserDtoDetail(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("company")
    val company: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
)