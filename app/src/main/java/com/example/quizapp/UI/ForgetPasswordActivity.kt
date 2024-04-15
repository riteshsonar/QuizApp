package com.example.quizapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityForgetPasswordBinding
import kotlinx.coroutines.launch

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSubmitDetails.setOnClickListener {
            if (binding.edtMobile.text?.length != 10){

            }else{
                validationApi()
            }

        }
    }

    private fun validationApi() {
        binding.progressLayout.visibility = View.VISIBLE
        lifecycleScope.launch {

            //Call Api flow
            binding.progressLayout.visibility = View.GONE
        }
    }
}