package com.eugene.data.entity.mapper

import com.eugene.data.entity.PasswordEntity
import com.eugene.domain.model.PasswordModel
import javax.inject.Inject

class PasswordEntityDataMapper @Inject constructor() {

    fun transform(passwordEntity : PasswordEntity) : PasswordModel {
        return PasswordModel(
            name = passwordEntity.name,
            description = passwordEntity.description,
            time = passwordEntity.time,
            hash = passwordEntity.hash,
            id = passwordEntity.id
        )
    }

    fun transformEntityList(passwordEntityList: List<PasswordEntity>) : List<PasswordModel> =
        passwordEntityList.map(::transform)

    fun transform(passwordModel: PasswordModel) : PasswordEntity{
        return PasswordEntity(
            name = passwordModel.name,
            description = passwordModel.description,
            time = passwordModel.time,
            hash = passwordModel.hash,
            id = passwordModel.id
        )
    }

    fun transformModelList(passwordModelList : List<PasswordModel>) : List<PasswordEntity> =
        passwordModelList.map(::transform)

}