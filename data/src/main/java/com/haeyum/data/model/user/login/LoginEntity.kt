/*
 * Created by PangMoo on 2022/12/16
 */

/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.model.user.login

@kotlinx.serialization.Serializable
data class LoginEntity(
    val id: String,
    val major: String,
    val name: String,
    val email: String,
    val imageUrl: String,
)