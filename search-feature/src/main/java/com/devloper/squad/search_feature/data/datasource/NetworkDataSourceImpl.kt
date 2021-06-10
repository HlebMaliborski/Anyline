package com.devloper.squad.search_feature.data.datasource

import com.devloper.squad.search_feature.data.api.ApiService
import com.devloper.squad.search_feature.data.dto.UserDtoDetail

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {

    override suspend fun getUser(login: String): UserDtoDetail {
        return apiService.getUser(login)
    }
}

interface NetworkDataSource {

    suspend fun getUser(login: String): UserDtoDetail
}