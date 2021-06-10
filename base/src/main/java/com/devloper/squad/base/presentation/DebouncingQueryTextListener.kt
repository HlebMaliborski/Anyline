package com.devloper.squad.base.presentation

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val debouncingPeriod: Long = 50_0,
    private val additionalAction: () -> Unit,
    private val onDebouncingQueryTextChange: (String?) -> Unit,
) : SearchView.OnQueryTextListener {

    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        additionalAction()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncingPeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}