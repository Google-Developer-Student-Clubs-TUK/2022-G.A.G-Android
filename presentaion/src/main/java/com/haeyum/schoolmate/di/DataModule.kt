/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.di

import com.haeyum.data.repository.profile.ProfileDataSource
import com.haeyum.data.repository.profile.ProfileDataSourceImpl
import com.haeyum.data.repository.profile.ProfileRepositoryImpl
import com.haeyum.domain.repository.ProfileRepository
import com.haeyum.domain.usecase.profile.GetProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideProfileDataSource(): ProfileDataSource = ProfileDataSourceImpl()

    @Singleton
    @Provides
    fun provideProfileRepository(profileDataSource: ProfileDataSource): ProfileRepository = ProfileRepositoryImpl(profileDataSource)
}