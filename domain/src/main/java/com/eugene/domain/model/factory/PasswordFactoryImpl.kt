package com.eugene.domain.model.factory

import com.eugene.domain.hasher.Hasher
import com.eugene.domain.model.PasswordModel

class PasswordFactoryImpl (private val hasher: Hasher) : PasswordFactory{

    override fun create(password: String, name: String, description: String): PasswordModel =
        PasswordModel(
            name = name,
            hash = hasher.hash(password),
            description = description,
            time = System.currentTimeMillis(),
            id = 0
        )

    override fun update(
        password: String?,
        name: String?,
        description: String?,
        passwordModel: PasswordModel
    ): PasswordModel =
        PasswordModel(
            name = name ?: passwordModel.name,
            description = description ?: passwordModel.description,
            hash = if(password != null) hasher.hash(password) else passwordModel.hash,
            time = System.currentTimeMillis(),
            id = passwordModel.id
        )
}