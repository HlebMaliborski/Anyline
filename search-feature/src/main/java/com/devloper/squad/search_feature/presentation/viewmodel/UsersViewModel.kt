package com.devloper.squad.search_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devloper.squad.navigation.Navigator
import com.devloper.squad.search_feature.domain.model.UserItem
import com.devloper.squad.search_feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UsersViewModel(
    private val navigator: Navigator,
    private val repository: UserRepository
) : ViewModel() {

    private val initialState: UserViewState by lazy { UserViewState() }
    private val _uiState: MutableStateFlow<UserViewState> = MutableStateFlow(initialState)

    // To not provide possibility of emitting data from Views we provide immutable interface
    val uiState: StateFlow<UserViewState> = _uiState

    private val _message = MutableSharedFlow<Message>()
    val message = _message

    // For clean view it would be better to move each case to appropriate function
    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.OnDetailPage -> {
                viewModelScope.launch {
                    navigator.openDetailView(event.login)
                }
            }

            is UsersEvent.OnSearch -> {
                setState(UserViewState(showProgress = true, scrollList = false))
                viewModelScope.launch {
                    repository.searchUsers(false, event.query)
                        .cachedIn(viewModelScope).collectLatest {
                            setState(
                                currentState().copy(
                                    data = it,
                                    showProgress = false,
                                    scrollList = false
                                )
                            )
                        }
                }
            }

            is UsersEvent.OnResetSearch -> {
                setState(
                    currentState().copy(
                        data = PagingData.empty(),
                        scrollList = false
                    )
                )
            }
            UsersEvent.OnError -> {
                // Instead of string we need to pass some Id to fetch string through resources.
                // But for now it's okay
                setMessage { Message.ShowMessage("Request limit is 10 per minute. Try later") }
            }
            UsersEvent.OnScrollList -> {
                setState(
                    currentState().copy(
                        scrollList = true
                    )
                )
            }
        }
    }

    private fun setState(state: UserViewState) {
        _uiState.value = state
    }

    private fun currentState() = _uiState.value

    // To display message once we need to use SharedFlow
    private fun setMessage(builder: () -> Message) {
        val effectValue = builder()
        viewModelScope.launch { _message.emit(effectValue) }
    }

    sealed class Message {
        data class ShowMessage(val message: String) : Message()
    }

    sealed class UsersEvent {
        data class OnSearch(val query: String) : UsersEvent()
        data class OnDetailPage(val login: String) : UsersEvent()
        object OnResetSearch : UsersEvent()
        object OnError : UsersEvent()
        object OnScrollList : UsersEvent()
    }

    // Here we can use domain model, as for example. It depends, in many cases we can avoid of creating
    // appropriate model for each layer. We can use one model for every layer.

    data class UserViewState(
        val data: PagingData<UserItem>? = null,
        val showProgress: Boolean = false,
        val scrollList: Boolean = false
    )
}