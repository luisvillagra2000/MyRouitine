package com.example.myroutine.repositories

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

class RoutineRepositoryTest {

    private lateinit var repository: RoutineRepositoryImpl
    private val apiService: ApiService = mock()

    @Before
    fun setUp() {
        repository = RoutineRepositoryImpl(apiService)
    }

    @Test
    fun `test getRoutine returns WorkoutPlan`() = runBlocking {
        whenever(apiService.generateWorkoutPlan(any())).thenReturn(mockWorkoutResponse)

        val result = repository.getRoutine(UserInfo())

        assertEquals(mockWorkoutPlan, result)
    }

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
}
