/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.domain.repository

interface DeviceRepository {
    suspend fun getDeviceUuid(): String
    suspend fun getSavedUuid(): String?
    suspend fun setSavedUuid(uuid: String)
    suspend fun getPersonalKey(): String?
    suspend fun setPersonalKey(key: String)
    suspend fun setId(id: String)
    suspend fun getId(): String?
}