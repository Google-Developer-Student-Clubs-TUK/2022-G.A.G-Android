/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.data.profile

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val major: String,
    val imageUrl: String,
    val isAlarmOn: Boolean,
    val isProfileVisible: Boolean,
)