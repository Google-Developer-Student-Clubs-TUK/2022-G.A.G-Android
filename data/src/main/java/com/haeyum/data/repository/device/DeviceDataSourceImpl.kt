/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.repository.device

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class DeviceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DeviceDataSource {
    private companion object {
        val DEVICE_UUID_KEY = stringPreferencesKey("device_uuid")
        val PERSONAL_KEY_KEY = stringPreferencesKey("personal_key")
        val ID_KEY = stringPreferencesKey("id")
    }

    override suspend fun getDeviceUuid(): String = UUID.randomUUID().toString()

    override suspend fun getSavedUuid(): String? = dataStore.data.map { preferences ->
        preferences[DEVICE_UUID_KEY]
    }.firstOrNull()

    override suspend fun setSavedUuid(uuid: String) {
        dataStore.edit { preferences ->
            preferences[DEVICE_UUID_KEY] = uuid
        }
    }

    override suspend fun getPersonalKey(): String? = dataStore.data.map { preferences ->
        preferences[PERSONAL_KEY_KEY]
    }.firstOrNull()

    override suspend fun setPersonalKey(key: String) {
        dataStore.edit { preferences ->
            preferences[PERSONAL_KEY_KEY] = key
        }
    }

    override suspend fun getId(): String? = dataStore.data.map { preferences ->
        preferences[ID_KEY]
    }.firstOrNull()

    override suspend fun setId(id: String) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = id
        }
    }
}