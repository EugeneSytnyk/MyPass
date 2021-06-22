package com.eugene.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PasswordEntity (
        @PrimaryKey(autoGenerate = true) val id : Int,
        val name : String,
        val description: String,
        val time : Long,
        val hash : String
)