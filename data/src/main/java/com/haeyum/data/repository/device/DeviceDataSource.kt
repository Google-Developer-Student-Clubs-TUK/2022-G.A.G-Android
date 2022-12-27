/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.repository.device

interface DeviceDataSource {
    suspend fun getDeviceUuid(): String
    suspend fun getSavedUuid(): String?
    suspend fun setSavedUuid(uuid: String)
    suspend fun getPersonalKey(): String?
    suspend fun setPersonalKey(key: String)
    suspend fun getId(): String?
    suspend fun setId(id: String)
}