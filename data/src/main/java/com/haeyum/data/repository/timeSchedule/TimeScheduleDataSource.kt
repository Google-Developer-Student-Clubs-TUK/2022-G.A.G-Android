package com.haeyum.data.repository.timeSchedule

import com.haeyum.data.model.timeSchedule.TimeScheduleResponse

interface TimeScheduleDataSource {
    suspend fun getTimeSchedule(): TimeScheduleResponse
}