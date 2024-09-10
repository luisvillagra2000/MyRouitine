package com.example.myroutine.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfo: UserInfoEntity)

    @Query("SELECT * FROM user_info LIMIT 1")
    suspend fun getUserInfo(): UserInfoEntity?
}
