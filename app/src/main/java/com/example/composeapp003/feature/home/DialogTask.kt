package com.example.composeapp003.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.getSelectedDate
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTask(
    onConfirm: (task: String, day: String, time: String) -> Unit,
    onCancel: () -> Unit
) {
    var task by remember { mutableStateOf("") }
    val day = rememberDatePickerState(initialSelectedDate = LocalDate.now())
    val time = rememberTimePickerState(
        initialHour = LocalTime.now().hour,
        initialMinute = LocalTime.now().minute,
        is24Hour = true
    )
    var showDataPicker by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = task,
            onValueChange = { newTask ->
                task = newTask
            },
            placeholder = { Text("Задача") }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = day.getSelectedDate().toString()
            )
            TextButton(
                onClick = { showDataPicker = true },
                elevation = ButtonDefaults.buttonElevation(2.dp)
            ) {
                Text("Выбрать Дату")
            }
        }
        if (showDataPicker)
            DatePickerDialog(
                onDismissRequest = { showDataPicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDataPicker = false
                        }
                    ) {
                        Text("Ввод")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDataPicker = false }
                    ) {
                        Text("Отмена")
                    }
                },

                ) {
                DatePicker(
                    state = day
                )
            }
        TimeInput(
            state = time
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                    onCancel()
                },
                elevation = ButtonDefaults.buttonElevation(1.dp)
            ) {
                Text("Отмена")
            }
            TextButton(
                onClick = {
                    onConfirm(
                        task,
                        day.getSelectedDate().toString(),
                        "%d:%d".format(time.hour, time.minute)
                    )
                },
                elevation = ButtonDefaults.buttonElevation(1.dp)
            ) {
                Text("Подтвердить")
            }
        }
    }
}