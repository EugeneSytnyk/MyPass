package com.eugene.app.di.module

import android.app.Application
import androidx.room.Room
import com.eugene.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class RoomModule(mApplication : Application) {

    private val appDatabase : AppDatabase =
        Room.databaseBuilder(mApplication, AppDatabase::class.java, "mypass_database").build()

    @Singleton
    @Provides
    fun provideAppDatabase() = appDatabase

    @Singleton
    @Provides
    fun providePasswordDao() = appDatabase.passwordDao()

}