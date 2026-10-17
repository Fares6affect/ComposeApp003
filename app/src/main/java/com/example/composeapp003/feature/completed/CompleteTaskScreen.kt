package com.example.composeapp003.feature.completed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp003.Task
import com.example.composeapp003.core.ui.components.ListTask
import com.example.composeapp003.core.ui.components.TypeVisTask

@Composable
fun CompleteTaskScreen() {

    val allTask = remember {
        mutableStateListOf(
            Task("2131", "3242", "32423"),
            Task("213123444364336564564564565464566546446364364", "3223442", "32424353"),
            Task("2131234", "3242234", "324352223")
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Завершенные задачи",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700)
            )
        )
        ListTask(listTask = allTask, onChoise = TypeVisTask.COMPLETE_TASK.onChoise)
    }
}