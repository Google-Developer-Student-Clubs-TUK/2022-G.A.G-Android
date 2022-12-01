/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.repository

import com.haeyum.domain.data.profile.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile?
}