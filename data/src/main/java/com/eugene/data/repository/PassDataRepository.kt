package com.eugene.data.repository

import com.eugene.data.database.PasswordDao
import com.eugene.data.entity.mapper.PasswordEntityDataMapper
import com.eugene.domain.model.PasswordModel
import com.eugene.domain.repository.PassRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PassDataRepository @Inject constructor(
    private val passwordDao: PasswordDao,
    private val passwordEntityDataMapper: PasswordEntityDataMapper
) : PassRepository {

    override fun loadPasswords(): Observable<List<PasswordModel>> {
        return passwordDao.getAll().map(passwordEntityDataMapper::transformEntityList)
    }

    override fun loadPassword(id: Int): Observable<PasswordModel> {
        return passwordDao.getById(id).map(passwordEntityDataMapper::transform)
    }

    override fun addPassword(pass: PasswordModel): Completable {
        return passwordDao.add(passwordEntityDataMapper.transform(pass))
    }

    override fun updatePassword(pass: PasswordModel): Completable {
        return passwordDao.update(passwordEntityDataMapper.transform(pass))
    }

    override fun deletePassword(pass: PasswordModel): Completable {
        return passwordDao.delete(passwordEntityDataMapper.transform(pass))
    }
}