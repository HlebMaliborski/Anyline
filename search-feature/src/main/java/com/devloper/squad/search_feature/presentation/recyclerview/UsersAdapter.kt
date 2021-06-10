package com.devloper.squad.search_feature.presentation.recyclerview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.devloper.squad.search_feature.domain.model.UserItem

class UsersAdapter : PagingDataAdapter<UserItem, UsersViewHolder>(DIFF_UTILS) {

    var listener: ((item: UserItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.createHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {

        private val DIFF_UTILS = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean =
                oldItem == newItem
        }
    }
}