package com.example.taskflowm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val userId: String,
    val username: String,
    val email: String,
    val passwordHash: String,
    val createdAt: Long = System.currentTimeMillis(),
    val lastLogin: Long? = null,
    val isActive: Boolean = true
) : Serializable
