package com.haeyum.data.repository.timeSchedule

import com.haeyum.data.mapper.mapToDomain
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import com.haeyum.domain.repository.TimeScheduleRepository
import javax.inject.Inject

class TimeScheduleRepositoryImpl @Inject constructor(private val timeScheduleDataSource: TimeScheduleDataSource) :
    TimeScheduleRepository {
    override suspend fun getTimeSchedule(id: String, key:String): List<TimeSchedule>? =
        timeScheduleDataSource.getTimeSchedule(id, key).result?.map {
            it.mapToDomain()
        }
}