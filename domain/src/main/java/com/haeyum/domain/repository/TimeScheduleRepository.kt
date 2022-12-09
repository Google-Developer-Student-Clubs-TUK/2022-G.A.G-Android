package com.haeyum.domain.repository

import com.haeyum.domain.data.timeSchedule.TimeSchedule


interface TimeScheduleRepository {
    suspend fun getTimeSchedule(): List<TimeSchedule>?
}