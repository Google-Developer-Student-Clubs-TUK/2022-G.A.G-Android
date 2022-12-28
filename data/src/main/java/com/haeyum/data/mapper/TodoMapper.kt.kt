package com.haeyum.data.mapper

import com.haeyum.data.model.todo.TodoEntity
import com.haeyum.domain.data.todo.Todo
import utils.TimeUtil

//data로 받아오는걸 실제 필요한 domain형태로 바꿔준다.
    fun TodoEntity.mapToDomain() = Todo(
        name = name,
        deadline = TimeUtil.tomillisecondLong(deadline),
        isDone = isDone,
        subjectId  = subjectId,
    )
