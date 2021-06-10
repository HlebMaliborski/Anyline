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
import com.devloper.squad.search_feature.databinding.FragmentUserDetailBinding
import com.devloper.squad.search_feature.domain.model.UserDetail
import com.devloper.squad.search_feature.presentation.viewmodel.UserDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModel()

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            binding.userLogo.load(it.avatarUrl)

            if (it.name != null) {
                binding.userName.text = it.name
                binding.userName.visibility(true)
            }

            if (it.company != null) {
                binding.userCompany.text = it.company
                binding.userCompany.visibility(true)
            }

            if (it.htmlUrl != null) {
                binding.userUrl.text = it.htmlUrl
                binding.userUrl.visibility(true)
            }
        }
    }
}