package com.example.paddleorganizer.loginPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.paddleorganizer.R
import com.example.paddleorganizer.databinding.FragmentLoginBinding
import com.example.paddleorganizer.userInfo.SignUpRepository
import com.example.paddleorganizer.userInfo.UserDatabase

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userInfoDao

        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.navigatetoSignUp.observe(viewLifecycleOwner, Observer {
            hasFinished ->
            if (hasFinished == true){
                navigateToSignUp()
                loginViewModel.doneNavigatingSignUp()
            }
        })

       /* loginViewModel.errorToast.observe(viewLifecycleOwner, Observer {
            hasError ->
            if (hasError == true){
                Toast.makeText(requireContext(),"Please fill all Fields",Toast.LENGTH_SHORT).show()
                loginViewModel.doneToast()
            }
        })

        loginViewModel.errorToastEmail.observe(viewLifecycleOwner, Observer {
            hasError ->
            if (hasError == true){
                Toast.makeText(requireContext(),"User Doesn't Exist,Please Register",Toast.LENGTH_SHORT).show()
                loginViewModel.doneToastErrorEmail()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer {
                hasError ->
            if (hasError == true){
                Toast.makeText(requireContext(),"Please check your Password",Toast.LENGTH_SHORT).show()
                loginViewModel.doneToastInvalidPassword(
                )
            }
        })*/

        loginViewModel.navigateToWelcomeScreen.observe(viewLifecycleOwner, Observer {
                hasFinished ->
            if (hasFinished == true){
                navigateWelcomeScreen()
                loginViewModel.doneNavigatingWelcomeScreen()
            }
        })
        return binding.root
    }

    private fun navigateToSignUp(){
        val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun navigateWelcomeScreen() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeScreen2()
        NavHostFragment.findNavController(this).navigate(action)
    }


}