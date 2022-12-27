/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.repository.device

import com.haeyum.domain.repository.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val deviceDataSource: DeviceDataSource
) : DeviceRepository {
    override suspend fun getDeviceUuid(): String = deviceDataSource.getDeviceUuid()

    override suspend fun getSavedUuid(): String? = deviceDataSource.getSavedUuid()

    override suspend fun setSavedUuid(uuid: String) = deviceDataSource.setSavedUuid(uuid)

    override suspend fun getPersonalKey(): String? = deviceDataSource.getPersonalKey()

    override suspend fun setPersonalKey(key: String) = deviceDataSource.setPersonalKey(key)

    override suspend fun getId(): String? = deviceDataSource.getId()

    override suspend fun setId(id: String) = deviceDataSource.setId(id)
}