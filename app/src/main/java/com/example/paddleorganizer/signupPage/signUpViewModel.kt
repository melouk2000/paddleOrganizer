package com.example.paddleorganizer.signupPage

import android.app.Application
import androidx.annotation.IntegerRes
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paddleorganizer.userInfo.SignUpRepository
import com.example.paddleorganizer.userInfo.UserInfo
import com.example.paddleorganizer.userInfo.UserInfoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpViewModel(private val database: UserInfoDao, application: Application
) : AndroidViewModel(application), Observable {

    private var userData = MutableLiveData<UserInfo?>()
    @Bindable
    val inputFirstName = MutableLiveData<String?>()

    @Bindable
    val inputLastName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>().toString()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    @Bindable
    val inputAge = MutableLiveData<Int>()

    @Bindable
    val inputCountry = MutableLiveData<String>()

    @Bindable
    val inputCity = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _navigateTo=MutableLiveData<Boolean>()
    val navigateTo:LiveData<Boolean>
        get() = _navigateTo

    private val _errorToast=MutableLiveData<Boolean>()
    val errorToast:LiveData<Boolean>
        get() = _errorToast

    private val _errorToastEmail=MutableLiveData<Boolean>()
    val errorToastEmail:LiveData<Boolean>
        get() = _errorToastEmail

    init {
        initializeSignUp()
    }

    private fun initializeSignUp(){
        viewModelScope.launch {
            userData.value= getUserFromDatabase()
        }
    }


    private suspend fun getUserFromDatabase(): UserInfo? {
        return database.getUser()
    }


    fun onSignUpBtn(UserInfo: Any?) {
        viewModelScope.launch {
            val newUser = UserInfo()
            //fixme sol, doesn't have anything related to the code
            insert(newUser)
            userData.value= getUserFromDatabase()
        }

    }


    fun doneNavigating() {
        _navigateTo.value = false
    }

    fun donetoast() {
        _errorToast.value = false
    }

    fun donetoastEmail() {
        _errorToast.value = false
    }

//    private suspend fun insert(user:UserInfo){
//        database.insert(user)
//    }
    fun insert(user:UserInfo) = viewModelScope.launch {
        database.insert(user)
}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}

