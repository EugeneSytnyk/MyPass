package com.eugene.domain.hasher

interface Validator {

    fun validate(hash : String, password : String) : Boolean

}