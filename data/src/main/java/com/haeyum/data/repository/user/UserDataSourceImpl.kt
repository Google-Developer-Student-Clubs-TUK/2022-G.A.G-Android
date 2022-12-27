/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.repository.user

import com.haeyum.data.model.user.login.LoginRequest
import com.haeyum.data.model.user.login.LoginResponse
import com.haeyum.data.model.user.register.RegisterRequest
import com.haeyum.data.model.user.register.RegisterResponse
import com.haeyum.data.model.user.subjects.SubjectsRequest
import com.haeyum.data.model.user.subjects.SubjectsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(private val client: HttpClient): UserDataSource {
    override suspend fun postRegister(uuid: String): RegisterResponse = client.post("http://13.52.122.41:8080/v1/users/register") {
        setBody(RegisterRequest(uuid))
    }.body()

    override suspend fun postLogin(uuid: String, key: String, id: String, password: String): LoginResponse = client.post("http://13.52.122.41:8080/v1/users/login") {
        setBody(LoginRequest(uuid, key, id, password))
    }.body()

    override suspend fun fetchSubjects(id: String, key: String): SubjectsResponse = client.post("http://13.52.122.41:8080/v1/users/subjects"){
        setBody(SubjectsRequest(id, key))
    }.body()
}