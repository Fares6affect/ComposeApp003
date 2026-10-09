package com.example.composeapp003

data class Task(
    var task: String,
    val day: String,
    val time: String,
    var completed: Boolean = false)
