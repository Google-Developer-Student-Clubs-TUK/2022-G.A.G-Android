/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.mapper

import com.haeyum.data.model.profile.ProfileEntity
import com.haeyum.data.model.profile.SettingEntity
import com.haeyum.domain.data.profile.Profile
import com.haeyum.domain.data.profile.Setting

fun ProfileEntity.mapToDomain() = Profile(
    id = id,
    name = name,
    email = email,
    major = major,
    imageUrl = imageUrl,
    isAlarmOn = isAlarmOn,
    isProfileVisible = isProfileVisible,
)

fun SettingEntity.mapToDomain() = Setting(
    enabledProfile = enabledProfile,
    enabledNotification = enabledNotification
)