package com.example.myroutine.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myroutine.data.model.DayPlan
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.WorkoutPlan

@Entity(tableName = "workout_plan")
data class WorkoutPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val day: String,
    val minutes: Int,
    val exerciseList: String
)

fun DayPlan.toEntity() = WorkoutPlanEntity(
    day = this.day,
    minutes = this.minutes,
    exerciseList = this.exercises.joinToString(";") { it.name + ":" + it.repetitions }
)

fun List<WorkoutPlanEntity>.toDomainModel(): WorkoutPlan {
    val week = this.map { dayPlan ->
        DayPlan(
            day = dayPlan.day,
            minutes = dayPlan.minutes,
            exercises = dayPlan.exerciseList.split(";").map { exercise ->
                val (name, repetitions) = exercise.split(":")
                Exercise(name = name, repetitions = repetitions)
            }
        )
    }
    return WorkoutPlan(week = week)
}