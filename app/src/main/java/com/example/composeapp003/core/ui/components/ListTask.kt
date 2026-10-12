package com.example.composeapp003.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composeapp003.Task
import kotlin.random.Random

@Composable
fun ListTask(listTask: SnapshotStateList<Task>, onChoise: (task: Task) -> Boolean){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {
        items(listTask) { task ->
            if(onChoise(task)){
                val (completed, onChangeState) = remember { mutableStateOf(false) }
                val colorLine = remember {
                    Color(
                        Random.nextInt(255),
                        Random.nextInt(255),
                        Random.nextInt(255)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable(
                            onClick = { onChangeState(!completed) }
                        ),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        val (boxLine, checkBox, taskText, dayTimeText, arrow, calendar) = createRefs()
                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .background(colorLine)
                                .constrainAs(boxLine) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    height = Dimension.fillToConstraints
                                }
                        )
                        CircleCheckBox(
                            completed, onChangeState,
                            Modifier
                                .padding(10.dp)
                                .constrainAs(checkBox) {
                                    start.linkTo(boxLine.end)
                                    top.linkTo(parent.top)
                                }
                        )
                        Text(
                            task.task,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .constrainAs(taskText) {
                                    start.linkTo(checkBox.end)
                                    top.linkTo(parent.top)
                                    end.linkTo(arrow.start)
                                    horizontalBias = 0f
                                    width = Dimension.fillToConstraints
                                },
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "ic_calendar",
                            tint = Color.LightGray,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .constrainAs(calendar) {
                                    start.linkTo(checkBox.end)
                                    top.linkTo(taskText.bottom)
                                    horizontalBias = 0f
                                }
                        )
                        Text(
                            task.day + ", " + task.time,
                            textDecoration =
                                if (completed) TextDecoration.LineThrough
                                else TextDecoration.None,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .constrainAs(dayTimeText) {
                                    start.linkTo(calendar.end, 10.dp)
                                    top.linkTo(taskText.bottom)
                                    end.linkTo(arrow.start)
                                    horizontalBias = 0f
                                }
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "ic_arrow",
                            tint = Color.LightGray,
                            modifier = Modifier.constrainAs(arrow) {
                                end.linkTo(parent.end, 10.dp)
                                centerVerticallyTo(parent)
                            }
                        )
                    }
                }
            }
        }
    }
}