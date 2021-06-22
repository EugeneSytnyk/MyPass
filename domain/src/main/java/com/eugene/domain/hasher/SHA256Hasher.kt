package com.eugene.domain.hasher

import java.security.MessageDigest

class SHA256Hasher : Hasher {

    override fun hash(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}