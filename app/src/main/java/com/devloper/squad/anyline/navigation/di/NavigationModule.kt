package com.devloper.squad.anyline.navigation.di

import com.devloper.squad.anyline.navigation.NavigatorImpl
import com.devloper.squad.navigation.Navigator
import org.koin.dsl.module

val NavigationModule = module {
    single<Navigator> { get<NavigatorImpl>() }
}