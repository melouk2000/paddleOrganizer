package com.example.paddleorganizer.userInfo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserInfoDao {

    @Insert
    suspend fun insert(user: UserInfo)

    @Update
    suspend fun update(user: UserInfo)

    @Query("SELECT * from USER_INFO_TABLE WHERE userId = :key")
    suspend fun get(key:Long):UserInfo


    @Query("SELECT * FROM user_info_table WHERE email = :email")
    suspend fun getEmail(email:String):UserInfo?

    //getting a list of users
    @Query("SELECT * FROM USER_INFO_TABLE ORDER BY userId DESC ")
    fun getAllUsers():LiveData<List<UserInfo>>
    //getting user data
    @Query("SELECT * FROM USER_INFO_TABLE ORDER BY userId DESC LIMIT 1 ")
    suspend fun getUser():UserInfo?
}