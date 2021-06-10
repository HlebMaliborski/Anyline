package com.devloper.squad.base.presentation

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.observeWhenCreated(scope: LifecycleCoroutineScope, action: suspend (value: T) -> Unit) {
    scope.launchWhenCreated {
        collect(action)
    }
}

fun <T> Flow<T>.observeWhenStarted(scope: LifecycleCoroutineScope, action: suspend (value: T) -> Unit): Job {
    return scope.launchWhenStarted {
        collect(action)
    }
}

fun Flow<Boolean>.observeVisibleWhenStarted(scope: LifecycleCoroutineScope, vararg views: View) {
    scope.launchWhenStarted {
        collect {
            views.forEach { view -> view.isVisible = it }
        }
    }
}

fun <T> Flow<T>.observeWhenResumed(scope: LifecycleCoroutineScope, action: suspend (value: T) -> Unit) {
    scope.launchWhenResumed {
        collect(action)
    }
}