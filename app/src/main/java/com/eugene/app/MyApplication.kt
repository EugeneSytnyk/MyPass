package com.eugene.app

import android.app.Application
import com.eugene.app.MyApplication.Dagger.daggerApplicationComponent
import com.eugene.app.di.component.ApplicationComponent
import com.eugene.app.di.component.DaggerApplicationComponent
import com.eugene.app.di.module.DataModule
import com.eugene.app.di.module.DomainModule
import com.eugene.app.di.module.RoomModule

class MyApplication : Application() {

    companion object Dagger{
        lateinit var daggerApplicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerApplicationComponent = DaggerApplicationComponent.builder()
            .roomModule(RoomModule(this))
            .build()

    }
}