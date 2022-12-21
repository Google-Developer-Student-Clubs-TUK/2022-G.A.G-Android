/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.model.user.register

import com.haeyum.data.model.BaseResponseModel

@kotlinx.serialization.Serializable
data class RegisterResponse(
    override val code: Int,
    override val msg: String,
    override val result: RegisterEntity?
) : BaseResponseModel<RegisterEntity>