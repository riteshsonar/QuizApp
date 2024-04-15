package com.example.quizapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivitySignUpActivtiyBinding

class SignUpActivtiy : AppCompatActivity() {
    lateinit var binding: ActivitySignUpActivtiyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {

        }
    }
}