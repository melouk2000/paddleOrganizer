package com.example.paddleorganizer.userInfo

import androidx.annotation.WorkerThread
import com.example.paddleorganizer.userInfo.UserInfo
import com.example.paddleorganizer.userInfo.UserInfoDao

class SignUpRepository (private val dao: UserInfoDao){

    val users =dao.getAllUsers()
    @WorkerThread
    suspend fun insert(user :UserInfo){
        return dao.insert(user)
    }

    suspend fun getEmail(email:String):UserInfo?{
        return dao.getEmail(email)
    }


}