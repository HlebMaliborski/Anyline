package com.devloper.squad.navigation

// To provide different functionality for different
// modules we can create more interfaces to abstract navigation
const val LOGIN = "login"

interface Navigator {

    suspend fun openDetailView(login: String)
}