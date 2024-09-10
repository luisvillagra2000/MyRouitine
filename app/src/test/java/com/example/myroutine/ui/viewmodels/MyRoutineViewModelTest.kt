package com.example.myroutine.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myroutine.data.model.DayPlan
import com.example.myroutine.data.model.Exercise
import com.example.myroutine.data.model.UserInfo
import com.example.myroutine.data.model.WorkoutPlan
import com.example.myroutine.repositories.RoutineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MyRoutineViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: RoutineRepository

    private lateinit var viewModel: MyRoutineViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MyRoutineViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test uploadUserInfo updates userInfo correctly`() {
        Assert.assertEquals(UserInfo(), viewModel.userInfo)

        viewModel.uploadUserInfo(mockUserInfo)

        Assert.assertEquals(mockUserInfo, viewModel.userInfo)
    }

    @Test
    fun `test getRoutine success updates UiState to Success`() = runTest {
        whenever(repository.getRoutine(mockUserInfo)).thenReturn(mockWorkoutPlan)

        viewModel.uploadUserInfo(mockUserInfo)
        viewModel.getRoutine()

        testDispatcher.scheduler.advanceUntilIdle()

        Assert.assertTrue(viewModel.myRoutineUiState is MyRoutineUiState.Success)
        Assert.assertEquals(
            mockWorkoutPlan,
            (viewModel.myRoutineUiState as MyRoutineUiState.Success).workoutPlan
        )
    }

    @Test
    fun `test getRoutine failure updates UiState to Error`() = runTest {
        whenever(repository.getRoutine(mockUserInfo)).thenThrow(RuntimeException("API Error"))

        viewModel.uploadUserInfo(mockUserInfo)
        viewModel.getRoutine()

        testDispatcher.scheduler.advanceUntilIdle()

        Assert.assertTrue(viewModel.myRoutineUiState is MyRoutineUiState.Error)
    }

    @Test
    fun `test createNewRoutine resets UiState to Init`() {
        viewModel.uploadUserInfo(mockUserInfo)
        viewModel.createNewRoutine()

        Assert.assertTrue(viewModel.myRoutineUiState is MyRoutineUiState.Init)
        Assert.assertEquals(
            mockUserInfo,
            (viewModel.myRoutineUiState as MyRoutineUiState.Init).values
        )
    }

    private val mockUserInfo = UserInfo(
        age = "24",
        weight = "85",
        height = "185",
        selectedGoal = "Fitness",
        hoursPerWeek = "6",
        selectedDaysList = listOf("Monday", "Wednesday", "Friday")
    )

    val mockWorkoutPlan = WorkoutPlan(
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
