
package com.haeyum.domain.usecase.todo

import com.haeyum.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(private val todoRepository: TodoRepository) {
    suspend operator fun invoke() = todoRepository.getTodo()
}