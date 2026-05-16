package com.example.taskflowm.data.repository

import com.example.taskflowm.data.local.dao.UserDao
import com.example.taskflowm.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserById(userId: String): User? = userDao.getUserById(userId)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}
