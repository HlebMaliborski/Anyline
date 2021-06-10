package com.devloper.squad.search_feature.common

sealed class Result<T> {
    data class Success<T>(val data: List<T>) : Result<T>()
    data class Error(val error: Exception) : Result<Unit>()
}