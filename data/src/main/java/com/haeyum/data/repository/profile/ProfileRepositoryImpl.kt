/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.repository.profile

import com.haeyum.data.mapper.mapToDomain
import com.haeyum.domain.data.profile.Profile
import com.haeyum.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profileDataSource: ProfileDataSource) :
    ProfileRepository {
    override suspend fun getProfile(): Profile? =
        profileDataSource.getProfile().result?.mapToDomain()
}