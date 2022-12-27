/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.usecase.user

import javax.inject.Inject

/** 만약 personal key가 저장되어 있지 않다면 서버에 요청하여 personal key를 받아온다. **/
class RegisterIfNotRegisteredUseCase @Inject constructor(
    private val registerUseCase: PostRegisterUseCase
) {
    @kotlin.jvm.Throws(kotlin.Throwable::class)
    suspend operator fun invoke(uuid: String) = registerUseCase(uuid)?.publicKey
}