package com.example.taskflowm.data.local.dao

import androidx.room.*
import com.example.taskflowm.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: String): User?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("UPDATE users SET lastLogin = :timestamp WHERE userId = :userId")
    suspend fun updateLastLogin(userId: String, timestamp: Long)
}
