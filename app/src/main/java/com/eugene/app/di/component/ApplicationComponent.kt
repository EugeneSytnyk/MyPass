package com.eugene.app.di.component

import com.eugene.app.di.module.DataModule
import com.eugene.app.di.module.DomainModule
import com.eugene.app.di.module.RoomModule
import com.eugene.app.viewmodel.PassListViewModel
import com.eugene.app.viewmodel.PasswordViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, DataModule::class, RoomModule::class])
interface ApplicationComponent {

    fun inject(passListViewModel: PassListViewModel)

    fun inject(passwordViewModel: PasswordViewModel)

}