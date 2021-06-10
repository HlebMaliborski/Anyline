package com.devloper.squad.anyline.search

import com.devloper.squad.search_feature.data.api.provideApiService
import com.devloper.squad.search_feature.data.api.provideOkHttpClient
import com.devloper.squad.search_feature.data.api.provideRetrofit
import com.devloper.squad.search_feature.data.datasource.NetworkDataSource
import com.devloper.squad.search_feature.data.datasource.NetworkDataSourceImpl
import com.devloper.squad.search_feature.data.mapper.UserDataToDomainMapper
import com.devloper.squad.search_feature.data.mapper.UserDataToDomainMapperImpl
import com.devloper.squad.search_feature.data.mapper.UserDetailDataToDomainMapper
import com.devloper.squad.search_feature.data.mapper.UserDetailDataToDomainMapperImpl
import com.devloper.squad.search_feature.data.repository.UserRepositoryImpl
import com.devloper.squad.search_feature.domain.repository.UserRepository
import com.devloper.squad.search_feature.domain.usecase.GetUserUseCase
import com.devloper.squad.search_feature.presentation.viewmodel.UserDetailViewModel
import com.devloper.squad.search_feature.presentation.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SearchModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }

    single<NetworkDataSource> { NetworkDataSourceImpl(apiService = get()) }
    single<UserDataToDomainMapper> { UserDataToDomainMapperImpl() }
    single<UserDetailDataToDomainMapper> { UserDetailDataToDomainMapperImpl() }
    single<UserRepository> {
        UserRepositoryImpl(
            networkDataSource = get(),
            mapper = get(),
            mapperDetail = get(),
            apiService = get()
        )
    }

    factory { GetUserUseCase(repository = get()) }

    viewModel { UsersViewModel(navigator = get(), repository = get()) }
    viewModel { UserDetailViewModel(getUser = get()) }
}
