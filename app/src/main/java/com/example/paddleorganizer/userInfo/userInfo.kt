package com.example.paddleorganizer.userInfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var userId:Int=0,
    @ColumnInfo(name = "first_name")
    var firstName: String?="",
    @ColumnInfo(name = "last_name")
    var lastName: String?="",
    @ColumnInfo(name = "age")
    var age: Int = 0,
    @ColumnInfo(name = "country")
    var country: String?="",
    @ColumnInfo(name = "city")
    var city: String?="",
    @ColumnInfo(name = "email")
    var email: String?="",
    @ColumnInfo(name = "password")
    var password: String?=""
)