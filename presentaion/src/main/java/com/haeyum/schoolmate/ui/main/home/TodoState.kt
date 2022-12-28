package com.haeyum.schoolmate.ui.main.home

import com.haeyum.domain.data.todo.Todo

sealed class TodoState {
    object Loading : TodoState()
    object Error : TodoState()
    data class Success(val todo: Todo) : TodoState()
}

