package com.example.quizapp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvForgotPassword.setOnClickListener {
            Intent(this, ForgetPasswordActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.tvSignup.setOnClickListener {
            openRegister()
        }
        binding.btnContinue.setOnClickListener {
            if (binding.etUserName.text.toString() != "") {
                if (binding.etPassword.text.toString() != "") {
                    callLoginAPi()
                } else {
                    Toast.makeText(this, "Enter your password", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Enter your Mobile Number", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
    override fun onResume() {
        super.onResume()
    }
    private fun callLoginAPi() {
        binding.progressLayout.visibility = View.VISIBLE
            Intent(this,MainActivity::class.java).apply{
                startActivity(this)
            }
        binding.progressLayout.visibility = View.GONE


    }

    private fun openRegister() {
        Intent(this, SignUpActivtiy::class.java).apply {
            startActivity(this)
        }

    }
}