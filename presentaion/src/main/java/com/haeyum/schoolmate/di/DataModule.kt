/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.haeyum.data.repository.device.DeviceDataSource
import com.haeyum.data.repository.device.DeviceDataSourceImpl
import com.haeyum.data.repository.device.DeviceRepositoryImpl
import com.haeyum.data.repository.profile.ProfileDataSource
import com.haeyum.data.repository.profile.ProfileDataSourceImpl
import com.haeyum.data.repository.profile.ProfileRepositoryImpl
import com.haeyum.data.repository.timeSchedule.TimeScheduleDataSource
import com.haeyum.data.repository.timeSchedule.TimeScheduleDataSourceImpl
import com.haeyum.data.repository.timeSchedule.TimeScheduleRepositoryImpl
import com.haeyum.data.repository.todo.TodoDataSource
import com.haeyum.data.repository.todo.TodoDataSourceImpl
import com.haeyum.data.repository.todo.TodoRepositoryImpl
import com.haeyum.data.repository.user.UserDataSource
import com.haeyum.data.repository.user.UserDataSourceImpl
import com.haeyum.data.repository.user.UserRepositoryImpl
import com.haeyum.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideUserDataSource(client: HttpClient): UserDataSource = UserDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository =
        UserRepositoryImpl(userDataSource)

    @Singleton
    @Provides
    fun provideProfileDataSource(client: HttpClient): ProfileDataSource = ProfileDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideProfileRepository(profileDataSource: ProfileDataSource): ProfileRepository =
        ProfileRepositoryImpl(profileDataSource)



    @Singleton
    @Provides
    fun provideTodoDataSource(client: HttpClient): TodoDataSource = TodoDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideTodoRepository(todoDataSource: TodoDataSource) : TodoRepository =
        TodoRepositoryImpl(todoDataSource)

    @Singleton
    @Provides
    fun provideTimeScheduleDataSource(client: HttpClient): TimeScheduleDataSource = TimeScheduleDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideTimeScheduleRepository(timeScheduleDataSource: TimeScheduleDataSource) : TimeScheduleRepository =
        TimeScheduleRepositoryImpl(timeScheduleDataSource)



    @Singleton
    @Provides
    fun provideDeviceDataSource(dataStore: DataStore<Preferences>): DeviceDataSource = DeviceDataSourceImpl(dataStore)

    @Singleton
    @Provides
    fun provideDeviceRepository(deviceDataSource: DeviceDataSource): DeviceRepository =
        DeviceRepositoryImpl(deviceDataSource)
}