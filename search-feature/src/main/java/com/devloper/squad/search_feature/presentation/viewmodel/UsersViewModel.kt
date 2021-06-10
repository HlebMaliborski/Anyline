package com.devloper.squad.search_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devloper.squad.navigation.Navigator
import com.devloper.squad.search_feature.domain.model.UserItem
import com.devloper.squad.search_feature.domain.repository.GitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UsersViewModel(
    private val navigator: Navigator,
    private val repository: GitRepository
) : ViewModel() {

    private val initialState: UserViewState by lazy { UserViewState() }
    private val _uiState: MutableStateFlow<UserViewState> = MutableStateFlow(initialState)

    // To not provide possibility of emitting data from Views we provide immutable interface
    val uiState: StateFlow<UserViewState> = _uiState

    // For clean view it would be better to move each case to appropriate function
    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.OnDetailPage -> {
                viewModelScope.launch {
                    navigator.openDetailView(event.login)
                }
            }

            is UsersEvent.OnSearch -> {
                setState(UserViewState(showProgress = true))
                viewModelScope.launch {
                    repository.searchUsers(false, event.query)
                        .cachedIn(viewModelScope).collectLatest {
                            setState(
                                currentState().copy(
                                    data = it,
                                    showProgress = false
                                )
                            )
                        }
                }
            }

            is UsersEvent.ResetSearch -> {
                setState(
                    currentState().copy(
                        data = PagingData.empty()
                    )
                )
            }
        }
    }

    private fun setState(state: UserViewState) {
        _uiState.value = state
    }

    private fun currentState() = _uiState.value

    sealed class UsersEvent {
        data class OnSearch(val query: String) : UsersEvent()
        data class OnDetailPage(val login: String) : UsersEvent()
        object ResetSearch : UsersEvent()
    }

    // Here we can use domain model, as for example. It depends, in many cases we can avoid of creating
    // appropriate model for each layer. We can use one model for every layer.

    data class UserViewState(
        val data: PagingData<UserItem>? = null,
        val showProgress: Boolean = false
    )
}