/*
 * Created by PangMoo on 2022/12/16
 */

/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.model.user.login

@kotlinx.serialization.Serializable
data class LoginRequest(
    val uuid: String,
    val key: String,
    val id: String,
    val password: String
)