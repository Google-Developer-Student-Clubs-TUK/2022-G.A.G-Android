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
                    id = "ASKDKSA@2032",
                    name = "영어",
                    room = "e동 423호",
                    startTime = "15:30"
                ),
                TimeScheduleEntity(
                    id = "ASKDKSA@2032",
                    name = "수학",
                    room = "e동 423호",
                    startTime = "19:30"
                ),
                TimeScheduleEntity(
                    id = "ASKDKSA@2032",
                    name = "수학",
                    room = "e동 423호",
                    startTime = "20:30"
                ),
            )
        )
    }
}