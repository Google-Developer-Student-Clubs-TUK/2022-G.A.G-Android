package com.haeyum.schoolmate.ui.main.home

import com.haeyum.domain.data.timeSchedule.TimeSchedule

sealed class TimeScheduleState {
    object Loading : TimeScheduleState()
    object Error : TimeScheduleState()
    data class Success(val timeSchedule: TimeSchedule) : TimeScheduleState()
}

