package com.haeyum.data.repository.todo

import com.haeyum.data.mapper.mapToDomain
import com.haeyum.domain.data.todo.Todo

import com.haeyum.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDataSource: TodoDataSource) :
    TodoRepository {
    override suspend fun getTodo(): List<Todo>? =
        todoDataSource.getTodo().result?.map {
            it.mapToDomain()
        }
}