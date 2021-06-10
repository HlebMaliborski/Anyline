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
import com.devloper.squad.search_feature.databinding.FragmentSearchBinding
import com.devloper.squad.search_feature.presentation.recyclerview.UsersAdapter
import com.devloper.squad.search_feature.presentation.viewmodel.UsersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchAdapter: UsersAdapter by lazy {
        UsersAdapter().apply {
            listener = {
                viewModel.onEvent(UsersViewModel.UsersEvent.OnDetailPage(it.login))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // To use anyline SDK just to uncomment line
        //AnylineSDK.init(getString(R.string.anyline_license_key), context)
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
            val errorState = loadStates.source.append as? LoadState.Error
                             ?: loadStates.source.prepend as? LoadState.Error
                             ?: loadStates.append as? LoadState.Error
                             ?: loadStates.prepend as? LoadState.Error
            if (errorState != null) {
                viewModel.onEvent(UsersViewModel.UsersEvent.OnError)
            }
        }

        lifecycleScope.launch {
            searchAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collectLatest { viewModel.onEvent(UsersViewModel.UsersEvent.OnScrollList) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        binding.userSearch.setOnQueryTextListener(
            DebouncingQueryTextListener(
                lifecycle, additionalAction = {
                    binding.userSearch.clearFocus()
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
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
            setHasFixedSize(true)
            itemAnimator = null
            isMotionEventSplittingEnabled = false
        }
    }

    private fun renderState(viewState: UsersViewModel.UserViewState) {
        if (viewState.data != null) {
            lifecycleScope.launch {
                searchAdapter.submitData(viewState.data)
            }
        }
        if (viewState.scrollList) {
            binding.searchRecyclerView?.scrollToPosition(0)
        }
        binding.userProgressBar.visibility(viewState.showProgress)
    }
}