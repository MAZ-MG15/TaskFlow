package com.example.taskflowm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskflowm.databinding.ActivityDevInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.exitButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
