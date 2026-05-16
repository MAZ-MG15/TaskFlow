package com.example.taskflowm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.taskflowm.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.signInLink.setOnClickListener {
            finish()
        }

        binding.createAccountButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            authViewModel.signUp(username, email, password)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.authState.collect { state ->
                    when (state) {
                        is AuthViewModel.AuthState.Success -> {
                            Toast.makeText(this@SignUpActivity, "Account created! Please sign in.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        is AuthViewModel.AuthState.Error -> {
                            Toast.makeText(this@SignUpActivity, state.message, Toast.LENGTH_SHORT).show()
                        }
                        is AuthViewModel.AuthState.Loading -> {
                            // Show loading if needed
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
