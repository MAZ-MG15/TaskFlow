package com.example.taskflowm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflowm.data.model.User
import com.example.taskflowm.data.repository.AuthRepository
import com.example.taskflowm.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val user: User) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    fun signUp(username: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepository.signUp(username, email, password)
            result.onSuccess { user ->
                // Don't save token here, let them sign in manually
                _authState.value = AuthState.Success(user)
            }.onFailure {
                _authState.value = AuthState.Error(it.message ?: "Sign up failed")
            }
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepository.signIn(username, password)
            result.onSuccess { user ->
                _authState.value = AuthState.Success(user)
            }.onFailure {
                _authState.value = AuthState.Error(it.message ?: "Sign in failed")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            preferencesManager.clearToken()
        }
    }

    suspend fun isLoggedIn(): Boolean {
        return preferencesManager.userToken.first() != null
    }
}
