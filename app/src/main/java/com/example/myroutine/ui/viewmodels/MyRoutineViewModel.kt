package com.example.myroutine.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.repositories.RoutineRepository
import kotlinx.coroutines.launch

class MyRoutineViewModel(private val repository: RoutineRepository) :
    ViewModel() {

    var userInfo: UserInfo by mutableStateOf(UserInfo())
        private set

    var myRoutineUiState: MyRoutineUiState by mutableStateOf(MyRoutineUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            userInfo = repository.getUserInfo() ?: UserInfo()
            repository.getStoredWorkoutPlan()?.let {
                myRoutineUiState = MyRoutineUiState.Success(it)
            } ?: run {
                myRoutineUiState = MyRoutineUiState.Init(userInfo)
            }
        }
    }

    fun uploadUserInfo(newUserInfo: UserInfo) {
        userInfo = newUserInfo
    }

    fun getRoutine(language: String) {
        viewModelScope.launch {
            myRoutineUiState = MyRoutineUiState.Loading
            myRoutineUiState = try {
                MyRoutineUiState.Success(
                    repository.getRoutine(userInfo, language)
                )
            } catch (e: Exception) {
                MyRoutineUiState.Error
            }
        }
    }

    fun createNewRoutine() {
        myRoutineUiState = MyRoutineUiState.Init(userInfo)
    }
}

sealed interface MyRoutineUiState {
    data class Success(val workoutPlan: WorkoutPlan) : MyRoutineUiState
    object Error : MyRoutineUiState
    object Loading : MyRoutineUiState

    data class Init(val values: UserInfo) : MyRoutineUiState
}

