package com.haeyum.data.model.timeSchedule

@kotlinx.serialization.Serializable
data class TimeScheduleEntity(
    val id: String,
    val name: String,
    val room: String,
    val startTime: String,
    val endTime: String,
    val isPinned: Boolean,
)
