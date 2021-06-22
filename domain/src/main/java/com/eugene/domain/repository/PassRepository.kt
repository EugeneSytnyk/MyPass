package com.eugene.domain.repository

import com.eugene.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Observable

interface PassRepository {

    fun loadPasswords() : Observable<List<PasswordModel>>

    fun loadPassword(id : Int) : Observable<PasswordModel>

    fun addPassword(pass : PasswordModel) : Completable

    fun updatePassword(pass : PasswordModel) : Completable

    fun deletePassword(pass : PasswordModel) : Completable

}