/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.usecase.user

import com.haeyum.domain.repository.UserRepository
import javax.inject.Inject

class FetchSubjectUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(id: String, key: String) = userRepository.fetchSubjects(id, key)
}