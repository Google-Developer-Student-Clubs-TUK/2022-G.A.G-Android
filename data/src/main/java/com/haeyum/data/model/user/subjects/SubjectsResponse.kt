/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.model.user.subjects

import com.haeyum.data.model.BaseResponseModel

@kotlinx.serialization.Serializable
data class SubjectsResponse(
    override val code: Int,
    override val msg: String,
    override val result: List<SubjectsEntity>?
): BaseResponseModel<List<SubjectsEntity>>