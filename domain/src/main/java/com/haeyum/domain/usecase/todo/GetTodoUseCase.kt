package com.haeyum.domain.usecase.todo

import com.haeyum.domain.repository.TodoRepository
import com.haeyum.domain.usecase.device.GetIdUseCase
import com.haeyum.domain.usecase.device.GetPersonalKeyUseCase
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val getIdUseCase: GetIdUseCase,
    private val getPersonalKeyUseCase: GetPersonalKeyUseCase,
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke() =
        todoRepository.getTodo(getIdUseCase()!!, getPersonalKeyUseCase()!!)
}