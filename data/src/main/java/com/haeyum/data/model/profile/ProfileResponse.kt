/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.model.profile

import com.haeyum.data.model.BaseResponseModel

data class ProfileResponse(
    override val code: Int,
    override val message: String,
    override val result: ProfileEntity?
) : BaseResponseModel<ProfileEntity>