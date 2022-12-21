/*
 * Created by PangMoo on 2022/12/16
 */

/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.model.user.login

import com.haeyum.data.model.BaseResponseModel

@kotlinx.serialization.Serializable
data class LoginResponse(
    override val code: Int,
    override val msg: String,
    override val result: LoginEntity?
): BaseResponseModel<LoginEntity>