/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.model.user.register

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RegisterEntity(
//    @SerialName("public_key")
    val public_key: String
)