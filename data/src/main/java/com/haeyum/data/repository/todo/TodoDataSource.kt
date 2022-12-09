package com.haeyum.data.repository.todo

import com.haeyum.data.model.todo.TodoResponse

interface TodoDataSource {
    suspend fun getTodo(): TodoResponse
}