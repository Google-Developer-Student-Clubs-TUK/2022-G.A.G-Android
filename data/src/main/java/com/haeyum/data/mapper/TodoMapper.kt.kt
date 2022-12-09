package com.haeyum.data.mapper

import com.haeyum.data.model.todo.TodoEntity
import com.haeyum.domain.data.todo.Todo

//data로 받아오는걸 실제 필요한 domain형태로 바꿔준다.
    fun TodoEntity.mapToDomain() = Todo(
        subjectId = subjectId,
        name = name,
        deadline = deadline,
        isDone = isDone,
    )
