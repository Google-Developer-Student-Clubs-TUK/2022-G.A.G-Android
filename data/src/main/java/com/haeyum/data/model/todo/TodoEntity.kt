package com.haeyum.data.model.todo
import java.time.LocalDate

data class TodoEntity (
    val subjectId: Int,
    val name: String,
    val deadline: String,
    val isDone: Boolean,
    )
