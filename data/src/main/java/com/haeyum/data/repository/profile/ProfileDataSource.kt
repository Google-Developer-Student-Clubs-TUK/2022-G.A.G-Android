/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.repository.profile

import com.haeyum.data.model.profile.ProfileResponse

interface ProfileDataSource {
    suspend fun getProfile(): ProfileResponse
}