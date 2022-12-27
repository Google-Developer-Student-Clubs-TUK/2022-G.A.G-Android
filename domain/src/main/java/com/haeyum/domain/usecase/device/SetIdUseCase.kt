/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.usecase.device

import com.haeyum.domain.repository.DeviceRepository
import javax.inject.Inject

class SetIdUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    suspend operator fun invoke(id: String) = deviceRepository.setId(id)
}