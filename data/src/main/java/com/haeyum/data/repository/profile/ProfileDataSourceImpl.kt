/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.repository.profile

import com.haeyum.data.model.profile.ProfileEntity
import com.haeyum.data.model.profile.ProfileResponse
import com.haeyum.data.model.profile.SettingEntity

class ProfileDataSourceImpl : ProfileDataSource {
    override suspend fun getProfile(): ProfileResponse {
        // TODO 서버 API 나오면 변경
        return ProfileResponse(
            code = 0,
            message = "success",
            result = ProfileEntity(
                code = 0,
                name = "유광무",
                studentId = "2019156023",
                major = "컴퓨터공학부 소프트웨어전공",
                setting = SettingEntity(
                    enabledProfile = true,
                    enabledNotification = false
                )
            )
        )
    }
}