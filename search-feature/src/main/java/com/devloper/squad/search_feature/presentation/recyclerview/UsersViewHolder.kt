package com.devloper.squad.search_feature.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.devloper.squad.base.presentation.recyclerview.BaseViewHolder
import com.devloper.squad.search_feature.R
import com.devloper.squad.search_feature.databinding.ItemSearchBinding
import com.devloper.squad.search_feature.domain.model.UserItem

class UsersViewHolder(override val containerView: View, private val listener: ((item: UserItem) -> Unit)?) :
    BaseViewHolder<UserItem>(containerView) {

    private val binding = ItemSearchBinding.bind(itemView)

    override fun bind(data: UserItem) {
        binding.userName.text = data.login
        binding.userUrl.text = data.htmlUrl

        binding.logo.load(data.avatarUrl)

        if (listener != null) {
            containerView.setOnClickListener {
                listener.invoke(data)
            }
        }
    }

    companion object {

        fun createHolder(parent: ViewGroup, listener: ((item: UserItem) -> Unit)?): UsersViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)
            return UsersViewHolder(view, listener)
        }
    }
}