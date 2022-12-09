package com.haeyum.data.model.timeSchedule

import com.haeyum.data.model.BaseResponseModel

data class TimeScheduleResponse(
    override val code: Int,
    override val message: String,
    override val result: List<TimeScheduleEntity>?
) : BaseResponseModel<List<TimeScheduleEntity>>

