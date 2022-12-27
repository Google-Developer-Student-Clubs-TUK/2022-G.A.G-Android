/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.usecase.profile

import com.haeyum.domain.repository.ProfileRepository
import com.haeyum.domain.usecase.device.GetIdUseCase
import com.haeyum.domain.usecase.device.GetPersonalKeyUseCase
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val getIdUseCase: GetIdUseCase,
    private val getPersonalKeyUseCase: GetPersonalKeyUseCase,
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() =
        profileRepository.getProfile(getIdUseCase()!!, getPersonalKeyUseCase()!!)
}