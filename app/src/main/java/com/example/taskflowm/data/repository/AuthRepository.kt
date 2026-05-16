package com.example.taskflowm.data.repository

import com.example.taskflowm.data.local.dao.UserDao
import com.example.taskflowm.data.model.User
import com.example.taskflowm.util.EncryptionUtil
import com.example.taskflowm.util.PreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao,
    private val preferencesManager: PreferencesManager,
    private val encryptionUtil: EncryptionUtil
) {

    suspend fun signIn(username: String, password: String): Result<User> = runCatching {
        val user = userDao.getUserByUsername(username)
            ?: throw Exception("User not found")

        val isPasswordCorrect = encryptionUtil.verifyPassword(password, user.passwordHash)
        if (!isPasswordCorrect) {
            throw Exception("Incorrect password")
        }

        userDao.updateLastLogin(user.userId, System.currentTimeMillis())
        preferencesManager.saveToken(user.userId) // Using saveToken from my PreferencesManager
        user
    }

    suspend fun signUp(username: String, email: String, password: String): Result<User> = runCatching {
        val existingUsername = userDao.getUserByUsername(username)
        if (existingUsername != null) {
            throw Exception("Username already in use")
        }

        val existingEmail = userDao.getUserByEmail(email)
        if (existingEmail != null) {
            throw Exception("Email already registered")
        }

        val hashedPassword = encryptionUtil.hashPassword(password)
        val newUser = User(
            userId = java.util.UUID.randomUUID().toString(),
            username = username,
            email = email,
            passwordHash = hashedPassword
        )

        userDao.insertUser(newUser)
        newUser
    }

    suspend fun getCurrentUser(): User? {
        // Need to adapt this to my PreferencesManager flow if necessary
        return null 
    }

    fun signOut() {
        // Adapt to clearToken
    }
}
