package com.haeyum.data.repository.todo

import com.haeyum.data.model.todo.TodoEntity
import com.haeyum.data.model.todo.TodoResponse

class TodoDataSourceImpl : TodoDataSource {
    override suspend fun getTodo(): TodoResponse {
        //임시용
        return TodoResponse(
            code = 0,
            message = "success",
            result = listOf(
                TodoEntity(
                    subjectId = 1,
                    name = "영어",
                    deadline = "11.21 오전 11:00",
                    isDone = false
                ),
                TodoEntity(
                    subjectId = 1,
                    name = "영어",
                    deadline = "11.21 오전 11:00",
                    isDone = false
                ),
                TodoEntity(
                    subjectId = 1,
                    name = "영어",
                    deadline = "11.21 오전 11:00",
                    isDone = false
                )
            )
        )
    }
}