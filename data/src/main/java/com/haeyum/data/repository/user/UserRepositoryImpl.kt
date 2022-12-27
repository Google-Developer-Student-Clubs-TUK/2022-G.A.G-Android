/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.repository.user

import com.haeyum.data.mapper.mapToDomain
import com.haeyum.domain.data.user.Login
import com.haeyum.domain.data.user.Register
import com.haeyum.domain.data.user.Subject
import com.haeyum.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSource) :
    UserRepository {
    override suspend fun postRegister(uuid: String): Register? =
        userDataSource.postRegister(uuid).result?.mapToDomain()

    override suspend fun postLogin(
        uuid: String,
        key: String,
        id: String,
        password: String
    ): Login? = userDataSource.postLogin(uuid, key, id, password).result?.mapToDomain()

    override suspend fun fetchSubjects(id: String, key: String): List<Subject>? {
        return userDataSource.fetchSubjects(id, key).result?.mapToDomain()
    }
}