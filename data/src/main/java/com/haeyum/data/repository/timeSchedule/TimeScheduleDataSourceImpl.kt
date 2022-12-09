package com.haeyum.data.repository.timeSchedule

import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.data.model.timeSchedule.TimeScheduleResponse


class TimeScheduleDataSourceImpl : TimeScheduleDataSource {
    override suspend fun getTimeSchedule(): TimeScheduleResponse {
        //임시용
        return TimeScheduleResponse(
            code = 0,
            message = "success",
            result = listOf(
                TimeScheduleEntity(
                    id = 1,
                    name = "영어",
                    room = "e동 423호",
                    startTime = "15:30"
                ),
                TimeScheduleEntity(
                    id = 1,
                    name = "영어",
                    room = "e동 423호",
                    startTime = "15:30"
                ),
                TimeScheduleEntity(
                    id = 1,
                    name = "영어",
                    room = "e동 423호",
                    startTime = "15:30"
                ),
            )
        )
    }
}