/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.model.profile

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ProfileEntity(
    val id: String,
    val name: String,
    val email: String,
    val major: String,
    @SerialName("image_url")
    val imageUrl: String,
    val isAlarmOn: Boolean,
    val isProfileVisible: Boolean,
)