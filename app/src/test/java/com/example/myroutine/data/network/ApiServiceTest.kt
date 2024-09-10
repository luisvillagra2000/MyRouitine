package com.example.myroutine.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ApiServiceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService


    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test generateWorkoutPlan`() = testScope.runTest {

        val request = WorkoutRequest(
            model = "openai/gpt-4o",
            messages = listOf(
                Message(role = "user", content = "Create a workout plan")
            )
        )
        val mockResponse = WorkoutResponse(
            choices = listOf(
                Choice(
                    Message(
                        role = "assistant",
                        content = "Here is your workout plan"
                    )
                )
            )
        )

        whenever(apiService.generateWorkoutPlan(request)).thenReturn(mockResponse)

        val response = apiService.generateWorkoutPlan(request)

        assertEquals(mockResponse, response)
    }

}