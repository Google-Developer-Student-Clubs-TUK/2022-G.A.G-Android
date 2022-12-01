/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.model.profile

data class ProfileEntity(
    val code: Int,
    val name: String,
    val studentId: String,
    val major: String,
    val setting: SettingEntity
)