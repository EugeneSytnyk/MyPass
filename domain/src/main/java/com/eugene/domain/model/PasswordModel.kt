package com.eugene.domain.model

data class PasswordModel(
    val name : String,
    val description: String,
    val time : Long,
    val hash : String,
    val id : Int
)