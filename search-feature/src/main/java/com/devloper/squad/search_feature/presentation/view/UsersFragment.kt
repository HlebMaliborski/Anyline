package com.devloper.squad.search_feature.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.devloper.squad.base.presentation.DebouncingQueryTextListener
import com.devloper.squad.base.presentation.observeWhenStarted
import com.devloper.squad.base.presentation.visibility
import com.devloper.squad.search_feature.R
import com.devloper.squad.search_feature.presentation.recyclerview.UsersAdapter
import com.devloper.squad.search_feature.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_search.searchRecyclerView
import kotlinx.android.synthetic.main.fragment_search.userProgressBar
import kotlinx.android.synthetic.main.fragment_search.userSearch
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModel()

    private val searchAdapter: UsersAdapter by lazy {
        UsersAdapter().apply {
            listener = {
                viewModel.onEvent(UsersViewModel.UsersEvent.OnDetailPage(it.login))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.observeWhenStarted(lifecycleScope) { viewState ->
            renderState(viewState)
        }

        viewModel.message.observeWhenStarted(lifecycleScope) { message ->
            when (message) {
                is UsersViewModel.Message.ShowMessage -> {
                    Toast.makeText(context, message.message, LENGTH_SHORT).show()
                }
            }
        }

        searchAdapter.loadStateFlow.observeWhenStarted(lifecycleScope) { loadStates ->
            handleLoadState(loadStates.refresh)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        userSearch.setOnQueryTextListener(
            DebouncingQueryTextListener(
                lifecycle, additionalAction = {
                    userSearch.clearFocus()
                }
            ) { newText ->
                newText?.let {
                    if (it.isEmpty()) {
                        viewModel.onEvent(UsersViewModel.UsersEvent.OnResetSearch)
                    } else {
                        viewModel.onEvent(UsersViewModel.UsersEvent.OnSearch(it))
                    }
                }
            }
        )
    }

    private fun initRecyclerView() {
        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
            setHasFixedSize(true)
            itemAnimator = null
            isMotionEventSplittingEnabled = false
        }
    }

    // Need to move this logic to viewModel and create
    private fun handleLoadState(state: LoadState) {
        when (state) {
            is LoadState.Error -> {
                viewModel.onEvent(UsersViewModel.UsersEvent.OnError)
            }
            else -> Log.d("Request state", state.toString())
        }
    }

    private fun renderState(viewState: UsersViewModel.UserViewState) {
        if (viewState.data != null) {
            lifecycleScope.launch {
                searchAdapter.submitData(viewState.data)
            }
        }
        userProgressBar.visibility(viewState.showProgress)
    }
}