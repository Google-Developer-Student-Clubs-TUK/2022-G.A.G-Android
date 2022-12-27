/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.data.mapper

import com.haeyum.data.model.user.login.LoginEntity
import com.haeyum.data.model.user.register.RegisterEntity
import com.haeyum.data.model.user.subjects.SubjectsEntity
import com.haeyum.domain.data.user.Login
import com.haeyum.domain.data.user.Register
import com.haeyum.domain.data.user.Subject

fun RegisterEntity.mapToDomain() = Register(publicKey)

fun LoginEntity.mapToDomain() = Login(
    id = id,
    major = major,
    name = name,
    email = email,
    imageUrl = imageUrl
)

fun List<SubjectsEntity>.mapToDomain() =
    map { Subject(id = it.id, name = it.name, isPinned = it.isPinned) }