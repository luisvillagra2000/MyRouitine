package com.example.myroutine.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class WorkoutPlanDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var workoutPlanDao: WorkoutPlanDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        workoutPlanDao = database.workoutPlanDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetWorkoutPlan() = runBlocking {
        mockWorkoutPlanEntities.forEach {
            workoutPlanDao.insertWorkoutPlan(it)
        }

        val retrievedWorkoutPlans = workoutPlanDao.getWorkoutPlan()

        assertEquals(retrievedWorkoutPlans.map { it.copy(id = 0) }, mockWorkoutPlanEntities.map { it.copy(id = 0) })
    }

    @Test
    fun clearWorkoutPlan() = runBlocking {
        mockWorkoutPlanEntities.forEach {
            workoutPlanDao.insertWorkoutPlan(it)
        }

        workoutPlanDao.clearWorkoutPlans()

        val retrievedWorkoutPlans = workoutPlanDao.getWorkoutPlan()

        assertEquals(retrievedWorkoutPlans, emptyList<WorkoutPlanEntity>() )
    }

    private val mockWorkoutPlanEntities = listOf(
        WorkoutPlanEntity(
            day = "Monday",
            minutes = 60,
            exerciseList = "Push-ups:3 sets of 15;Squats:3 sets of 20"
        ),
        WorkoutPlanEntity(
            day = "Wednesday",
            minutes = 45,
            exerciseList = "Pull-ups:3 sets of 10;Lunges:3 sets of 12"
        ),
        WorkoutPlanEntity(
            day = "Friday",
            minutes = 50,
            exerciseList = "Deadlift:4 sets of 8;Bench Press:4 sets of 10"
        )
    )
}