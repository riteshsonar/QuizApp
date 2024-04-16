package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpNavigation()
        //AppDatabase.getInstance(this@MainActivity).QuizDao
        findNavController(R.id.navHostFragment).navigate(R.id.navigation)

    }

    private fun setUpNavigation() {
        //setSupportActionBar(binding.toolbar)
        navController = findNavController(R.id.navHostFragment)


        //setupActionBarWithNavController(navController, binding.drawerLayout)
        //binding.navigationView.setupWithNavController(navController)
    }
}