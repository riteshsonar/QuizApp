package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:  FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuizFragment("Ritesh"))
        }
        return binding.root
    }

}