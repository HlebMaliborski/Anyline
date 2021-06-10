package com.devloper.squad.anyline.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.devloper.squad.anyline.R
import com.devloper.squad.navigation.LOGIN
import com.devloper.squad.navigation.Navigator

/**
 * As we have only one module it's okay to have navigator in the app module. Maybe in future
 * it would be better to move navigation functionality into separate module.
 */
class NavigatorImpl(private val navController: NavController) :
    Navigator {

    override suspend fun openDetailView(login: String) {
        val bundle = bundleOf(LOGIN to login)
        navController.navigate(R.id.searchDetailFragment, bundle)
    }

    override suspend fun pop(block: () -> Unit) {
        if (navController.popBackStack().not()) {
            block()
        } else {
            navController.popBackStack()
        }
    }
}

