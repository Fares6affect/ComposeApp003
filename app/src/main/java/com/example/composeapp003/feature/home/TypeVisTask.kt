package com.example.composeapp003.feature.home

import com.example.composeapp003.Task

enum class TypeVisTask(val onChoise:(task: Task)-> Boolean){
    ALL_TASK({true}),
    ACTIVE_TASK({task ->
        task.completed
    }),
    COMPLETE_TASK({task ->
        !task.completed
    })
}