package com.devloper.squad.search_feature.data.mapper

import com.devloper.squad.base.common.Mapper
import com.devloper.squad.search_feature.data.dto.UserDtoDetail
import com.devloper.squad.search_feature.domain.model.UserDetail

class UserDetailDataToDomainMapperImpl : UserDetailDataToDomainMapper {

    override fun map(data: UserDtoDetail): UserDetail {
        return UserDetail(
            id = data.id,
            avatarUrl = data.avatarUrl,
            company = data.company,
            htmlUrl = data.htmlUrl,
            name = data.name
        )
    }
}

interface UserDetailDataToDomainMapper : Mapper<UserDtoDetail, UserDetail>