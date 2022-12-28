/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.data.timeSchedule

data class TimeSchedule(
    val name: String,
    val room: String,
    val startTime: Long,
    val endTime: Long,
)