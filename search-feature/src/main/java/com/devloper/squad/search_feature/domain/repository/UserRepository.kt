package com.devloper.squad.search_feature.domain.repository

import androidx.paging.PagingData
import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.domain.model.UserItem
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(login: String): UserDetail

    /**
     * @param reloadData if we need to reload data for BE (API)
     */
    fun searchUsers(reloadData: Boolean = false, query: String): Flow<PagingData<UserItem>>
}