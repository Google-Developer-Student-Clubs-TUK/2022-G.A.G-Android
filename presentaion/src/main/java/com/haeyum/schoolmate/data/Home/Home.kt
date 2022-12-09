package com.haeyum.schoolmate.data.Home

data class TimeScheduleDto(
    val location: String,
    val major: String,
    val time : String, //일단 문자열? int인지는 모르겠음
)

data class TodoDto(
    val name : String,
    val major : String,
    val time : String,
    val is_submit : Boolean
)