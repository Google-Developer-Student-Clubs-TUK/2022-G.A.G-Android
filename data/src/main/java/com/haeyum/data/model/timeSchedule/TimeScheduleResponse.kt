package com.haeyum.data.model.timeSchedule


import com.haeyum.data.model.BaseResponseModel

@kotlinx.serialization.Serializable
data class TimeScheduleResponse(
    override val code: Int,
    override val msg: String,
    override val result: List<TimeScheduleEntity>?
) : BaseResponseModel<List<TimeScheduleEntity>>

