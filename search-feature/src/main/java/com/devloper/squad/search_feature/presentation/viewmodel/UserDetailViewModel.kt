package com.devloper.squad.search_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.domain.usecase.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getUser: GetUserUseCase,
) : ViewModel() {

    private val initialState: UserDetailViewState by lazy { UserDetailViewState() }
    private val _uiState: MutableStateFlow<UserDetailViewState> = MutableStateFlow(initialState)
    val uiState = _uiState.filterNotNull()

    fun onEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.OnOpenBrowser -> {

            }
            is UserDetailEvent.OnRequestUser -> {
                viewModelScope.launch {
                    val user = getUser(event.login)
                    setState(
                        currentState().copy(
                            userDetail = user
                        )
                    )
                }
            }
        }
    }

    private fun setState(state: UserDetailViewState) {
        _uiState.value = state
    }

    private fun currentState() = _uiState.value

    sealed class UserDetailEvent {
        data class OnOpenBrowser(val url: String) : UserDetailEvent()
        data class OnRequestUser(val login: String) : UserDetailEvent()
    }

    data class UserDetailViewState(
        var userDetail: UserDetail? = null,
    )
}