package com.example.myroutine.repositories

import com.example.myroutine.data.db.UserInfoDao
import com.example.myroutine.data.db.UserInfoEntity
import com.example.myroutine.data.db.WorkoutPlanDao
import com.example.myroutine.data.db.WorkoutPlanEntity
import com.example.myroutine.data.model.DayPlan
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.data.network.ApiService
import com.example.myroutine.data.network.Choice
import com.example.myroutine.data.network.Message
import com.example.myroutine.data.network.WorkoutResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RoutineRepositoryImplTest {

    private lateinit var repository: RoutineRepositoryImpl
    private val apiService: ApiService = mock()
    private val userInfoDao: UserInfoDao = mock()
    private val workoutPlanDao: WorkoutPlanDao = mock()

    @Before
    fun setUp() {
        repository = RoutineRepositoryImpl(apiService, userInfoDao, workoutPlanDao)
    }

    @Test
    fun `getRoutine should insert user info and workout plan`() = runBlocking {
        whenever(apiService.generateWorkoutPlan(any())).thenReturn(mockWorkoutResponse)

        val result = repository.getRoutine(mockUserInfo)

        assertEquals(mockWorkoutPlan,result)
    }

    @Test
    fun `getUserInfo should return user info from database`() = runBlocking {
        whenever(userInfoDao.getUserInfo()).thenReturn(mockUserInfoEntity)

        val result = repository.getUserInfo()

        assertEquals(mockUserInfo,result)
    }

    @Test
    fun `getStoredWorkoutPlan should return workout plan from database`() = runBlocking {
        whenever(workoutPlanDao.getWorkoutPlan()).thenReturn(mockWorkoutPlanEntities)

        val result = repository.getStoredWorkoutPlan()

        assertEquals(mockWorkoutPlan, result)
    }

    @Test
    fun `getRoutine should return WorkoutPlan when API returns valid data`() = runBlocking {
        whenever(apiService.generateWorkoutPlan(any())).thenReturn(mockWorkoutResponse)

        val result = repository.getRoutine(UserInfo())

        assertEquals(mockWorkoutPlan, result)
    }

    private val mockUserInfo = UserInfo(
        age = "24",
        weight = "85",
        height = "185",
        selectedGoal = "muscle gain",
        hoursPerWeek = "5",
        selectedDaysList = listOf("Monday", "Wednesday", "Friday")
    )

    private val mockJsonResponse = """
            {
                "week": [
                    {"day": "Monday", "minutes": 60, "exercises": [{"name": "Push-ups", "repetitions": "3 sets of 15"}, {"name": "Squats", "repetitions": "3 sets of 20"}]},
                    {"day": "Wednesday", "minutes": 45, "exercises": [{"name": "Pull-ups", "repetitions": "3 sets of 10"}, {"name": "Lunges", "repetitions": "3 sets of 12"}]},
                    {"day": "Friday", "minutes": 50, "exercises": [{"name": "Deadlift", "repetitions": "4 sets of 8"}, {"name": "Bench Press", "repetitions": "4 sets of 10"}]},
                    {"day": "Saturday", "minutes": 0, "exercises": []},
                    {"day": "Sunday", "minutes": 0, "exercises": []}
                ]
            }
        """.trimIndent()

    private val mockWorkoutResponse = WorkoutResponse(
        choices = listOf(
            Choice(message = Message(role = "assistant", content = mockJsonResponse))
        )
    )

    private val mockWorkoutPlan = WorkoutPlan(
        week = listOf(
            DayPlan(
                day = "Monday",
                minutes = 60,
                exercises = listOf(
                    Exercise(name = "Push-ups", repetitions = "3 sets of 15"),
                    Exercise(name = "Squats", repetitions = "3 sets of 20")
                )
            ),
            DayPlan(
                day = "Wednesday",
                minutes = 45,
                exercises = listOf(
                    Exercise(name = "Pull-ups", repetitions = "3 sets of 10"),
                    Exercise(name = "Lunges", repetitions = "3 sets of 12")
                )
            ),
            DayPlan(
                day = "Friday",
                minutes = 50,
                exercises = listOf(
                    Exercise(name = "Deadlift", repetitions = "4 sets of 8"),
                    Exercise(name = "Bench Press", repetitions = "4 sets of 10")
                )
            )
        )
    )

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

    private val mockUserInfoEntity = UserInfoEntity(
        age = "24",
        weight = "85",
        height = "185",
        selectedGoal = "muscle gain",
        hoursPerWeek = "5",
        selectedDaysList = "Monday;Wednesday;Friday"
    )
}
