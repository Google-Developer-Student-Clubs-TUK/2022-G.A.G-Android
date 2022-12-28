package com.haeyum.data.model.timeSchedule

@kotlinx.serialization.Serializable
data class TimeScheduleRequest(
    val id: String,
    val key: String
)