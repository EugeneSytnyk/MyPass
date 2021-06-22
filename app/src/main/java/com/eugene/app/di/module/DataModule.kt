package com.eugene.app.di.module

import com.eugene.data.repository.PassDataRepository
import com.eugene.domain.repository.PassRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providePassRepository(passDataRepository: PassDataRepository) : PassRepository = passDataRepository

}