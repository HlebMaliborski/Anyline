package com.devloper.squad.search_feature.data.dto


import com.google.gson.annotations.SerializedName

data class UsersDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int,
    val nextPage: Int? = null
)