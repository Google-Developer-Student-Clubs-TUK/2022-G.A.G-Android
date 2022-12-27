/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.usecase.device

import com.haeyum.domain.repository.DeviceRepository
import javax.inject.Inject

/** 만약 UUID가 저장되어 있지 않다면 생성하고 저장한다. **/
class GenerateAndSaveUuidIfNotExistsUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    suspend operator fun invoke(): String = deviceRepository.getSavedUuid() ?: run {
        val uuid = deviceRepository.getDeviceUuid()
        deviceRepository.setSavedUuid(uuid)
        uuid
    }
}