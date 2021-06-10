package com.devloper.squad.search_feature.data.api

import com.devloper.squad.search_feature.data.dto.UserDtoDetail
import com.devloper.squad.search_feature.data.dto.UsersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiPaths.USERS)
    suspend fun searchUsers(
        @Query("q") query: String = "qwe",
        @Query("page") page: Int = 1,
        @Query("per_page") itemsPerPage: Int = 30
    ): UsersDto

    @GET(ApiPaths.USER)
    suspend fun getUser(@Path("login") login: String): UserDtoDetail
}