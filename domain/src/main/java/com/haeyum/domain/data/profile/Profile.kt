/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.data.profile

data class Profile(
    val code: Int,
    val name: String,
    val studentId: String,
    val major: String,
    val setting: Setting
)