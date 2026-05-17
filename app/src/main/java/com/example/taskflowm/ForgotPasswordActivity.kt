package com.example.taskflowm

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.taskflowm.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.sendInstructionsButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            if (isValidEmail(email)) {
                showSuccessDialog(email)
            } else {
                Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showSuccessDialog(email: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.email_sent_title))
            .setMessage(getString(R.string.email_sent_desc, email))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}
