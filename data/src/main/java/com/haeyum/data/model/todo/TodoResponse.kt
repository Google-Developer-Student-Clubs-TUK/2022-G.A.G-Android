package com.haeyum.data.model.todo

import com.haeyum.data.model.BaseResponseModel


@kotlinx.serialization.Serializable
data class TodoResponse(
    override val code: Int,
    override val msg: String,
    override val result: List<TodoEntity>?
) : BaseResponseModel<List<TodoEntity>>

