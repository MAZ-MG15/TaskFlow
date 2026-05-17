package com.example.taskflowm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflowm.data.model.User
import com.example.taskflowm.data.repository.UserRepository
import com.example.taskflowm.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            preferencesManager.userToken.collectLatest { userId ->
                if (userId != null) {
                    _user.value = userRepository.getUserById(userId)
                } else {
                    _user.value = null
                }
            }
        }
    }

    fun updateUser(username: String, email: String) {
        viewModelScope.launch {
            val currentUser = _user.value ?: return@launch
            val updatedUser = currentUser.copy(username = username, email = email)
            userRepository.updateUser(updatedUser)
            _user.value = updatedUser
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            val currentUser = _user.value ?: return@launch
            userRepository.deleteUser(currentUser)
            preferencesManager.clearToken()
        }
    }
}
