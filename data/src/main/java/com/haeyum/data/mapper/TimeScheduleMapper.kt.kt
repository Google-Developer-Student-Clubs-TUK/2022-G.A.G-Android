package com.haeyum.data.mapper

import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import utils.TimeUtil

//data로 받아오는걸 실제 필요한 domain형태로 바꿔준다.
    fun TimeScheduleEntity.mapToDomain() = TimeSchedule(
        name = name,
        room = room,
        startTime = TimeUtil.tomillisecond(startTime),
    )

