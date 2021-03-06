package com.devloper.squad.search_feature.domain.usecase

import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: UserRepository
) {

    suspend operator fun invoke(login: String): UserDetail {
        return withContext(dispatcher) {
            repository.getUser(login)
        }
    }
}