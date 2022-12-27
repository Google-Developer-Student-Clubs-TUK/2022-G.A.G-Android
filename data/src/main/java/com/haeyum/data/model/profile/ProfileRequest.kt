/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.model.profile

@kotlinx.serialization.Serializable
data class ProfileRequest(
    val id: String,
    val key: String
)