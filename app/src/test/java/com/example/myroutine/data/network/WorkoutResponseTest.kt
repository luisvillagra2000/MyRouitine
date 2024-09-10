package com.example.myroutine.data.network

import com.example.myroutine.data.model.DayPlan
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.WorkoutPlan
import org.junit.Assert
import org.junit.Test

class WorkoutResponseTest {
    @Test
    fun `test to WorkoutPlan fun`() {
        Assert.assertEquals(mockWorkoutPlan, mockWorkoutResponseSimple.toWorkoutPlan())
        Assert.assertEquals(mockWorkoutPlan, mockWorkoutResponseWithJsonTag.toWorkoutPlan())
    }

    private val mockJsonResponseSimple = """
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
    private val mockJsonResponseWithJsonTag = """
        ```json
            {
                "week": [
                    {"day": "Monday", "minutes": 60, "exercises": [{"name": "Push-ups", "repetitions": "3 sets of 15"}, {"name": "Squats", "repetitions": "3 sets of 20"}]},
                    {"day": "Wednesday", "minutes": 45, "exercises": [{"name": "Pull-ups", "repetitions": "3 sets of 10"}, {"name": "Lunges", "repetitions": "3 sets of 12"}]},
                    {"day": "Friday", "minutes": 50, "exercises": [{"name": "Deadlift", "repetitions": "4 sets of 8"}, {"name": "Bench Press", "repetitions": "4 sets of 10"}]},
                    {"day": "Saturday", "minutes": 0, "exercises": []},
                    {"day": "Sunday", "minutes": 0, "exercises": []}
                ]
            }
        ```
        """.trimIndent()


    private val mockWorkoutResponseSimple = WorkoutResponse(
        choices = listOf(
            Choice(message = Message(role = "assistant", content = mockJsonResponseSimple))
        )
    )
    private val mockWorkoutResponseWithJsonTag = WorkoutResponse(
        choices = listOf(
            Choice(message = Message(role = "assistant", content = mockJsonResponseWithJsonTag))
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