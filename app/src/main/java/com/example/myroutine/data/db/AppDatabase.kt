package com.example.myroutine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInfoEntity::class, WorkoutPlanEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun workoutPlanDao(): WorkoutPlanDao
}