package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.room.AppDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpNavigation()
        //AppDatabase.getInstance(this@MainActivity).QuizDao
    }


    override fun onSupportNavigateUp() = navigateUp(
        findNavController(R.id.navHostFragment), binding.drawerLayout
    )

    private fun setUpNavigation() {
        setSupportActionBar(binding.toolbar)
        navController = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.navigationView.setupWithNavController(navController)
    }
}