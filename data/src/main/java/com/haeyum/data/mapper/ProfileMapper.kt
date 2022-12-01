/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.mapper

import com.haeyum.data.model.profile.ProfileEntity
import com.haeyum.data.model.profile.SettingEntity
import com.haeyum.domain.data.profile.Profile
import com.haeyum.domain.data.profile.Setting

fun ProfileEntity.mapToDomain() = Profile(
    code = code,
    name = name,
    studentId = studentId,
    major = major,
    setting = setting.mapToDomain()
)

fun SettingEntity.mapToDomain() = Setting(
    enabledProfile = enabledProfile,
    enabledNotification = enabledNotification
)