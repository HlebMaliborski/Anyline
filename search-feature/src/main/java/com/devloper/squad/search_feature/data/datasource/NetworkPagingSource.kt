package com.devloper.squad.search_feature.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devloper.squad.search_feature.data.api.ApiService
import com.devloper.squad.search_feature.data.mapper.UserDataToDomainMapper
import com.devloper.squad.search_feature.data.repository.UserRepositoryImpl.Companion.PAGE_SIZE
import com.devloper.squad.search_feature.domain.model.UserItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1

class NetworkPagingSource(
    private val service: ApiService,
    private val query: String,
    private val mapper: UserDataToDomainMapper
) : PagingSource<Int, UserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = service.searchUsers(query, page, PAGE_SIZE)
            val items = response.items
            val nextKey = if (items.isEmpty()) {
                null
            } else {
                page + (params.loadSize / PAGE_SIZE)
            }

            LoadResult.Page(
                data = mapper.map(items),
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}