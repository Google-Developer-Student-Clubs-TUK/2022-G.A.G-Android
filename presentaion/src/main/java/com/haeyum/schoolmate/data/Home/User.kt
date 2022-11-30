package com.example.toy_proejct.data

data class TimeScheduel(
    val location: String,
    val major: String,
    val time : String, //일단 문자열? int인지는 모르겠음
)

data class Todo(
    val name : String,
    val major : String,
    val time : String,
    val is_submit : Boolean
)