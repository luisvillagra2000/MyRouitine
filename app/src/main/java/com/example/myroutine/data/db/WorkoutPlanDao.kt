package com.example.myroutine.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlanEntity)

    @Query("SELECT * FROM workout_plan")
    suspend fun getWorkoutPlan(): List<WorkoutPlanEntity>

    @Query("DELETE FROM workout_plan")
    suspend fun clearWorkoutPlans()
}