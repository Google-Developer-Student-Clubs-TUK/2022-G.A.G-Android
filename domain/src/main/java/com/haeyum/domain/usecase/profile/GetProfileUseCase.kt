/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.usecase.profile

import com.haeyum.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke() = profileRepository.getProfile()
}