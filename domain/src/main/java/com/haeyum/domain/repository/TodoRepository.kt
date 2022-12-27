package com.haeyum.domain.repository

import com.haeyum.domain.data.todo.Todo

interface TodoRepository {
    suspend fun getTodo(id: String, key: String): List<Todo>?
}