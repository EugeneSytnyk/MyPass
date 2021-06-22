package com.eugene.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eugene.data.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun passwordDao(): PasswordDao

}
