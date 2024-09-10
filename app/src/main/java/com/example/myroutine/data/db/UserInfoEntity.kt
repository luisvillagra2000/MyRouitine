package com.example.myroutine.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myroutine.data.model.UserInfo

@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val age: String,
    val weight: String,
    val height: String,
    val selectedGoal: String,
    val hoursPerWeek: String,
    val selectedDaysList: String
)

fun UserInfo.toEntity() = UserInfoEntity(
    age = this.age,
    weight = this.weight,
    height = this.height,
    selectedGoal = this.selectedGoal,
    hoursPerWeek = this.hoursPerWeek,
    selectedDaysList = this.selectedDaysList.joinToString(";")
)

fun UserInfoEntity.toDomainModel() = UserInfo(
    age = this.age,
    weight = this.weight,
    height = this.height,
    selectedGoal = this.selectedGoal,
    hoursPerWeek = this.hoursPerWeek,
    selectedDaysList = this.selectedDaysList.split(";")
)