package com.example.paddleorganizer.loginPage

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.paddleorganizer.userInfo.SignUpRepository
import com.example.paddleorganizer.userInfo.UserInfoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val database: UserInfoDao, application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToSignUp = MutableLiveData<Boolean>()

    val navigatetoSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

    private val _navigateToWelcomeScreen = MutableLiveData<Boolean>()

    val navigateToWelcomeScreen: LiveData<Boolean>
        get() = _navigateToWelcomeScreen

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errorToastEmail: LiveData<Boolean>
        get() = _errorToastEmail

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun signUP() {
        _navigateToSignUp.value=true
    }

    //Function triggered When the Login Button is Clicked, Via Binding.
    fun loginButton() {
        _navigateToWelcomeScreen.value = true
    }

    fun checkFieldsNullability(){
        if (inputEmail.value == null||inputPassword.value == null){
            _errorToast.value = true
        }
    }

    private suspend fun checkUserValid(){
        //fixme
        val email = database.getEmail(inputEmail.value!!)
        if (email != null){
            if (email.password == inputPassword.value){
                inputEmail.value = null
                inputPassword.value = null
            } else{
                _errorToastInvalidPassword
            }
        }else{
            _errorToastEmail
        }
    }


    fun doneNavigatingSignUp(){
        _navigateToSignUp.value = false
    }

    fun doneNavigatingWelcomeScreen(){
        _navigateToWelcomeScreen.value = false
    }

    fun doneToast(){
        _errorToast.value = false
    }

    fun doneToastErrorEmail(){
        _errorToastEmail.value = false
    }

    fun doneToastInvalidPassword() {
        _errorToastInvalidPassword .value = false
    }



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}