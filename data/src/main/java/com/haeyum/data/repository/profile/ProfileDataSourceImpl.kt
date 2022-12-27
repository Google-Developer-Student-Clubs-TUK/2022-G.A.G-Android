/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.repository.profile

import com.haeyum.data.model.profile.ProfileRequest
import com.haeyum.data.model.profile.ProfileResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(private val client: HttpClient) : ProfileDataSource {
    override suspend fun getProfile(id: String, key: String): ProfileResponse = client.post("http://13.52.122.41:8080/v1/users/profile") {
        setBody(ProfileRequest(id, key))
    }.body()
}