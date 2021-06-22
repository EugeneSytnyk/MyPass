package com.eugene.domain.model.factory

import com.eugene.domain.model.PasswordModel

interface PasswordFactory {

    fun create(password: String, name: String, description: String) : PasswordModel

    fun update(password : String?, name : String?, description: String?, passwordModel : PasswordModel) : PasswordModel

}