package com.eugene.domain.hasher

class ValidatorImpl (private val hasher : Hasher) : Validator {

    override fun validate(hash: String, password: String): Boolean {
        return hasher.hash(password) == hash
    }

}