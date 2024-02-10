package com.example.paddleorganizer.loginPage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paddleorganizer.userInfo.SignUpRepository
import com.example.paddleorganizer.userInfo.UserInfoDao

class LoginViewModelFactory(private  val dataSource: UserInfoDao,
                            private val application: Application
): ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown View Model Class")
        }
}