package com.haeyum.data.model.todo


@kotlinx.serialization.Serializable
data class TodoEntity (
    val subjectId: String,
    val name: String,
    val deadline: String,
    val isDone: Boolean,
    )
