package com.example.paddleorganizer.signupPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.paddleorganizer.R
import com.example.paddleorganizer.databinding.FragmentSignUpBinding
import com.example.paddleorganizer.userInfo.UserDatabase
import com.example.paddleorganizer.userInfo.UserInfo

class signUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userInfoDao

        val viewModelFactory = SignUpViewModelFactory(dataSource,application)

        val signUpViewModel =
            ViewModelProvider(this,viewModelFactory
            ).get(SignUpViewModel::class.java)

        binding.signUpViewModel=signUpViewModel

        binding.lifecycleOwner = this

        signUpViewModel.navigateTo.observe(viewLifecycleOwner) { hasFinished ->
            hasFinished?.let {
                navigateToLogin()
                signUpViewModel.onSignUpBtn(it)
                signUpViewModel.doneNavigating()
            }
        }

        /* signUpViewModel.errorToast.observe(viewLifecycleOwner, Observer {
             hasError ->
             if (hasError == true){
                 Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_SHORT).show()
                 signUpViewModel.donetoast()
             }
         })

         signUpViewModel.errorToastEmail.observe(viewLifecycleOwner, Observer {
                 hasError ->
             if (hasError == true){
                 Toast.makeText(requireContext(),"Email Already Taken",Toast.LENGTH_SHORT).show()
                 signUpViewModel.donetoastEmail()
             }
         })*/

        return binding.root
    }

    private fun navigateToLogin(){
        val action = signUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}



