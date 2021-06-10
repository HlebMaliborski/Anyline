package com.devloper.squad.search_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devloper.squad.search_feature.data.api.ApiService
import com.devloper.squad.search_feature.data.datasource.NetworkDataSource
import com.devloper.squad.search_feature.data.datasource.NetworkPagingSource
import com.devloper.squad.search_feature.data.mapper.UserDataToDomainMapper
import com.devloper.squad.search_feature.data.mapper.UserDetailDataToDomainMapper
import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.domain.model.UserItem
import com.devloper.squad.search_feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val mapper: UserDataToDomainMapper,
    private val mapperDetail: UserDetailDataToDomainMapper,
    private val apiService: ApiService
) : UserRepository {

    override suspend fun getUser(login: String): UserDetail {
        return try {
            mapperDetail.map(networkDataSource.getUser(login))
        } catch (e: Exception) {
            // We should handle exception properly.
            // Either take cached data or try to request data again. Or we can implement
            // Either<out L, out R> pattern for sending result from data layer.
            // For now I sending just predefined object
            UserDetail()
        }
    }

    override fun searchUsers(reloadData: Boolean, query: String): Flow<PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false, initialLoadSize = 1),
            pagingSourceFactory = { NetworkPagingSource(apiService, query, mapper) }
        ).flow
    }

    companion object {

        const val PAGE_SIZE = 10
    }
}