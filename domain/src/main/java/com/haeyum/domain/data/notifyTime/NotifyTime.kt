package com.haeyum.domain.data.notifyTime

data class NotifyTime(
    val isLeft: Boolean, //true : 수업이 남아있음 // false : 수업종료 & 금일의 수업이 존재하지 않음
    val message: String,
    val leftTime: String?,
    val time: String?,
    val major: String?,
)