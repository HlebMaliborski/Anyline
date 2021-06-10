package com.devloper.squad.search_feature.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.devloper.squad.base.presentation.observeWhenCreated
import com.devloper.squad.base.presentation.visibility
import com.devloper.squad.navigation.LOGIN
import com.devloper.squad.search_feature.R
import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.presentation.viewmodel.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.userCompany
import kotlinx.android.synthetic.main.fragment_user_detail.userLogo
import kotlinx.android.synthetic.main.fragment_user_detail.userName
import kotlinx.android.synthetic.main.fragment_user_detail.userUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observeWhenCreated(lifecycleScope) { viewState ->
            renderState(viewState.userDetail)
        }

        val login = arguments?.getString(LOGIN) ?: ""
        viewModel.onEvent(UserDetailViewModel.UserDetailEvent.OnRequestUser(login))
    }

    private fun renderState(userDetail: UserDetail?) {
        userDetail?.let {
            userLogo.load(it.avatarUrl)

            if (it.name != null) {
                userName.text = it.name
                userName.visibility(true)
            }

            if (it.company != null) {
                userCompany.text = it.company
                userCompany.visibility(true)
            }

            if (it.htmlUrl != null) {
                userUrl.text = it.htmlUrl
                userUrl.visibility(true)
            }
        }
    }
}