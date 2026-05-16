package com.example.taskflowm.util

import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptionUtil @Inject constructor() {

    fun hashPassword(password: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }

    fun verifyPassword(password: String, hash: String): Boolean {
        return hashPassword(password) == hash
    }
}
