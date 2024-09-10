package com.example.myroutine.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserInfoDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var userInfoDao: UserInfoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userInfoDao = database.userInfoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUserInfoAndGetUserInfo() = runBlocking {
        userInfoDao.insertUserInfo(mockUserInfoEntity)
        val retrievedUserInfo = userInfoDao.getUserInfo()

        assertEquals(mockUserInfoEntity, retrievedUserInfo?.copy(id = 0))
    }

    private val mockUserInfoEntity = UserInfoEntity(
        id = 0,
        age = "24",
        weight = "85",
        height = "185",
        selectedGoal = "muscle gain",
        hoursPerWeek = "5",
        selectedDaysList = "Monday;Wednesday;Friday"
    )
}
