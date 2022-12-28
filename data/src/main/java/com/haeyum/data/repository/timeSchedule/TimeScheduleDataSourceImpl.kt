package com.haeyum.data.repository.timeSchedule

import com.haeyum.data.model.timeSchedule.TimeScheduleRequest
import com.haeyum.data.model.timeSchedule.TimeScheduleResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject


class TimeScheduleDataSourceImpl @Inject constructor(private val client: HttpClient) :
    TimeScheduleDataSource {
    override suspend fun getTimeSchedule(id: String, key: String): TimeScheduleResponse =
        client.post("http://13.52.122.41:8080/v1/users/subjects/today") {
            setBody(TimeScheduleRequest(id, key))
        }.body()
}

