/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.repository.user

import com.haeyum.data.model.user.login.LoginResponse
import com.haeyum.data.model.user.register.RegisterResponse

interface UserDataSource {
    suspend fun postRegister(uuid: String): RegisterResponse
    suspend fun postLogin(uuid: String, key: String, id: String, password: String): LoginResponse
}