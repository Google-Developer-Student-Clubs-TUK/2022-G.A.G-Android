package com.haeyum.data.repository.todo

import com.haeyum.data.model.todo.TodoRequest
import com.haeyum.data.model.todo.TodoResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class TodoDataSourceImpl  @Inject constructor(private val client: HttpClient) : TodoDataSource {
    override suspend fun getTodo(id: String, key: String): TodoResponse =
        client.post("http://13.52.122.41:8080/v1/users/todos") {
            setBody(TodoRequest(id, key))
        }.body()
}
