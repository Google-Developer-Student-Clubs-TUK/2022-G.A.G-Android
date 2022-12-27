package com.haeyum.data.model.todo

@kotlinx.serialization.Serializable
data class TodoRequest(
    val id: String,
    val key: String
)