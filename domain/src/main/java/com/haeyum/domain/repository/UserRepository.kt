/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.domain.repository

import com.haeyum.domain.data.user.Login
import com.haeyum.domain.data.user.Register
import com.haeyum.domain.data.user.Subject

interface UserRepository {
    suspend fun postRegister(uuid: String): Register?
    suspend fun postLogin(uuid: String, key: String, id: String, password: String): Login?
    suspend fun fetchSubjects(id: String, key: String): List<Subject>?
}