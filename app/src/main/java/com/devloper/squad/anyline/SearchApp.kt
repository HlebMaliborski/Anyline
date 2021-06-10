package com.devloper.squad.anyline

import android.app.Application
import com.devloper.squad.anyline.navigation.di.NavigationModule
import com.devloper.squad.anyline.search.SearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchApp : Application() {

    private val startupModules = listOf(SearchModule, NavigationModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SearchApp)
            modules(startupModules)
        }
    }
}