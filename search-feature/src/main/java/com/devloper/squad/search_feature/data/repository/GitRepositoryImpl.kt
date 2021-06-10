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
import com.devloper.squad.search_feature.domain.repository.GitRepository
import kotlinx.coroutines.flow.Flow

class GitRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val mapper: UserDataToDomainMapper,
    private val mapperDetail: UserDetailDataToDomainMapper,
    private val apiService: ApiService
) : GitRepository {

/*    override suspend fun searchUsers(reloadData: Boolean, query: String): Flow<Users> {
        // Right now we will always make a request to BE to retrieve data, but it would be better
        // to implement cache DataSource to not have requests to frequent. Or, as for alternative we can
        // use StateFlow here instead of cache DataSource

        //Also, there is should be check for internet connection before call API
        return flow {
            emit(mapper.map(networkDataSource.searchUsers(query)))
        }.catch { exception ->
            Log.d("Ura", exception.toString())
            // We can handle situation if BE returns us some exception or whatever.
            // For example in case of error we can return cached data. Or error code.
        }
    }*/

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
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false, initialLoadSize = 1),
            pagingSourceFactory = { NetworkPagingSource(apiService, query, mapper) }
        ).flow
    }

    companion object {

        const val NETWORK_PAGE_SIZE = 10
    }
}