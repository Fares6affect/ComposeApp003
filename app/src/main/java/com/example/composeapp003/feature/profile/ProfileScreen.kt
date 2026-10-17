package com.example.composeapp003.feature.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@Composable
fun ProfileScreen(vm: ProfileViewModelLiveData = viewModel()) {
    /*val countValue by vm.count.observeAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(100) {
            Row(){
                Text(
                    "${countValue}",
                    modifier = Modifier
                        .height(50.dp)
                        .weight(4f)
                        .background(
                            Color(
                                Random.nextInt(255),
                                Random.nextInt(255),
                                Random.nextInt(255)
                            )
                        )
                )
                Button(
                    onClick = {vm.increaseCount()},
                    modifier = Modifier.weight(1f)
                ){
                    Text("+++++")
                }
            }
        }
    }*/
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(400.dp)
                .background(Color.LightGray)
        ) {
            val width = size.width
            val height = size.height
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = width, y = height),
                color = Color.Blue,
                strokeWidth = 12.0f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f,10f,10f, 10f), phase = 0f)
            )
            drawRect(
                color = Color.Red,
                size = Size(500f, 100f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawRoundRect(
                color = Color.Black,
                size = Size(100f,500f),
                topLeft = Offset(300f,300f),
                style = Stroke(
                    width = 3.dp.toPx()
                ),
                cornerRadius = CornerRadius(
                    30.dp.toPx(),
                    30.dp.toPx()
                )
            )
            drawCircle(
                color = Color.Black,
                center = Offset(200f,200f),
                radius = 100f
            )
            drawOval(
                color = Color.Black,
                topLeft = Offset(300f,300f),
                size = Size(width=100f,height=300f)
            )
        }
    }
}