/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.usecase.device

import com.haeyum.domain.repository.DeviceRepository
import javax.inject.Inject

class SetPersonalKeyUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    suspend operator fun invoke(uuid: String) = deviceRepository.setPersonalKey(uuid)
}