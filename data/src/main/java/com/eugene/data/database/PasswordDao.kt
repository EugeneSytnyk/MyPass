package com.eugene.data.database

import androidx.room.*
import com.eugene.data.entity.PasswordEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PasswordDao {

    @Query("SELECT * FROM PasswordEntity")
    fun getAll() : Observable<List<PasswordEntity>>

    @Query("SELECT * FROM PasswordEntity WHERE id = :id")
    fun getById(id : Int) : Observable<PasswordEntity>

    @Update
    fun update(passwordEntity: PasswordEntity) : Completable

    @Delete
    fun delete(passwordEntity: PasswordEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(passwordEntity: PasswordEntity) : Completable
}