package com.example.paddleorganizer.Screens.WelcomeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.paddleorganizer.R
import com.example.paddleorganizer.databinding.WelcomeScreenBinding

class welcome_Screen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:WelcomeScreenBinding = DataBindingUtil.inflate(inflater,R.layout.welcome_screen,container,false)
        return binding.root
    }


}