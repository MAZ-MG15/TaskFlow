package com.example.taskflowm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.taskflowm.databinding.ActivityUserInfoBinding
import com.example.taskflowm.databinding.DialogEditUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupBottomNav()
        observeUser()
        
        binding.signOutButton.setOnClickListener {
            // We should use AuthViewModel for sign out to clear preferences
            // but for now simple finish is fine if we just want to go back
            startActivity(Intent(this, SignInActivity::class.java))
            finishAffinity()
        }

        binding.editInfoButton.setOnClickListener {
            showEditUserDialog()
        }

        binding.deleteAccountLink.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Delete Account")
            .setMessage("Are you sure you want to permanently delete your account? All your tasks will be lost.")
            .setPositiveButton("DELETE") { _, _ ->
                userViewModel.deleteAccount()
                startActivity(Intent(this, SignInActivity::class.java))
                finishAffinity()
            }
            .setNegativeButton("CANCEL", null)
            .show()
    }

    private fun observeUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.user.collect { user ->
                    user?.let {
                        binding.usernameValue.text = it.username
                        binding.emailValue.text = it.email
                    }
                }
            }
        }
    }

    private fun showEditUserDialog() {
        val currentUser = userViewModel.user.value ?: return
        val dialogBinding = DialogEditUserBinding.inflate(LayoutInflater.from(this))
        dialogBinding.editUsername.setText(currentUser.username)
        dialogBinding.editEmail.setText(currentUser.email)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.saveUserButton.setOnClickListener {
            val newUsername = dialogBinding.editUsername.text.toString()
            val newEmail = dialogBinding.editEmail.text.toString()
            
            if (newUsername.isNotEmpty() && newEmail.isNotEmpty()) {
                userViewModel.updateUser(newUsername, newEmail)
                dialog.dismiss()
                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun setupBottomNav() {
        binding.navTasks.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }
        
        binding.navSchedule.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }

        binding.navFocus.setOnClickListener {
            startActivity(Intent(this, FocusActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }
    }
}
