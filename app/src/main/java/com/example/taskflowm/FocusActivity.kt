package com.example.taskflowm

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.taskflowm.databinding.ActivityFocusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FocusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFocusBinding
    private var timer: CountDownTimer? = null
    private var isTimerRunning = false
    private var timeLeftInMillis: Long = 1500000 // 25 minutes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFocusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
        updateCountDownText()

        binding.startStopButton.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                isTimerRunning = false
                binding.startStopButton.text = "START"
                binding.sessionLabel.text = "FINISHED"
            }
        }.start()

        isTimerRunning = true
        binding.startStopButton.text = "PAUSE"
    }

    private fun pauseTimer() {
        timer?.cancel()
        isTimerRunning = false
        binding.startStopButton.text = "START"
    }

    private fun resetTimer() {
        timer?.cancel()
        isTimerRunning = false
        timeLeftInMillis = 1500000
        updateCountDownText()
        binding.startStopButton.text = "START"
        binding.sessionLabel.text = "STAY FOCUSED"
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.timerTextView.text = timeLeftFormatted
    }

    private fun setupBottomNav() {
        binding.navTasks.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(0, 0)
        }

        binding.navSchedule.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
            finish()
            overridePendingTransition(0, 0)
        }

        binding.navProfile.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
            finish()
            overridePendingTransition(0, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
