package com.devloper.squad.search_feature.data.mapper

import com.devloper.squad.base.common.Mapper
import com.devloper.squad.search_feature.data.dto.Item
import com.devloper.squad.search_feature.domain.model.UserItem
import com.devloper.squad.search_feature.domain.model.Users

class UserDataToDomainMapperImpl : UserDataToDomainMapper {

    override fun map(data: List<Item>): Users {
        return Users().apply {
            addAll(data.map { userDtoItem ->
                UserItem(
                    id = userDtoItem.id,
                    login = userDtoItem.login,
                    avatarUrl = userDtoItem.avatarUrl,
                    htmlUrl = userDtoItem.htmlUrl
                )
            })
        }
    }
}

interface UserDataToDomainMapper : Mapper<List<Item>, Users>