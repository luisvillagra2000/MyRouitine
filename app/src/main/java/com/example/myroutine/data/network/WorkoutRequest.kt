package com.example.myroutine.data.network

data class WorkoutRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)
