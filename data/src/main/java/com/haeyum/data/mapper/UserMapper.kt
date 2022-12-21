/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.mapper

import com.haeyum.data.model.user.login.LoginEntity
import com.haeyum.data.model.user.register.RegisterEntity
import com.haeyum.domain.data.user.Login
import com.haeyum.domain.data.user.Register

fun RegisterEntity.mapToDomain() = Register(public_key)

fun LoginEntity.mapToDomain() = Login(
    studentId = studentId,
    major = major,
    name = name,
    email = email,
    ImageURL = ImageURL
)