package com.eugene.app.di.module

import com.eugene.domain.hasher.Hasher
import com.eugene.domain.hasher.SHA256Hasher
import com.eugene.domain.hasher.Validator
import com.eugene.domain.hasher.ValidatorImpl
import com.eugene.domain.model.factory.PasswordFactory
import com.eugene.domain.model.factory.PasswordFactoryImpl
import com.eugene.domain.repository.PassRepository
import com.eugene.domain.usecase.*
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun providePasswordFactory(hasher : Hasher) : PasswordFactory = PasswordFactoryImpl(hasher)

    @Singleton
    @Provides
    fun provideHasher(hasher : SHA256Hasher) : Hasher = hasher

    @Singleton
    @Provides
    fun provideValidator(hasher : Hasher) : Validator = ValidatorImpl(hasher)

    @Singleton
    @Provides
    fun provideSHA256Hasher() = SHA256Hasher()

    @Provides
    fun provideGetPassList(passRepository: PassRepository) =
        GetPassList(passRepository, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideGetPassById(passRepository: PassRepository) =
        GetPassById(passRepository, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideCreatePassInstance(passRepository: PassRepository, passwordFactory: PasswordFactory) =
        CreatePassInstance(passRepository, passwordFactory, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideUpdatePassInstance(passRepository: PassRepository, passwordFactory: PasswordFactory) =
        UpdatePassInstance(passRepository, passwordFactory, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideDeletePassInstance(passRepository: PassRepository) =
        DeletePassInstance(passRepository, Schedulers.io(), AndroidSchedulers.mainThread())

}