package com.ujangwahyu.app.di

import com.ujangwahyu.app.data.repository.DataRepository
import com.ujangwahyu.app.data.repository.Repository
import com.ujangwahyu.app.data.repository.local.LocalRepository
import com.ujangwahyu.app.data.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(
        local: LocalRepository,
        remote: RemoteRepository,
    ): Repository {
        return DataRepository(local, remote)
    }

}