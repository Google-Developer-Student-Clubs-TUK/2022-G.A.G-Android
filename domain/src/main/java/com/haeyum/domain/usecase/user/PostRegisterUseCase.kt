/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.domain.usecase.user

import com.haeyum.domain.repository.UserRepository
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(uuid: String) = userRepository.postRegister(uuid)
}
