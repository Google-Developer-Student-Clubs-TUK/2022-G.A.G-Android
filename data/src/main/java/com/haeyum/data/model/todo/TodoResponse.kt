package com.haeyum.data.model.todo

import com.haeyum.data.model.BaseResponseModel

data class TodoResponse(
    override val code: Int,
    override val message: String,
    override val result: List<TodoEntity>?
) : BaseResponseModel<List<TodoEntity>>
