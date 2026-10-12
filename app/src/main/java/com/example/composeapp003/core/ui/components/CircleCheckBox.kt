package com.example.composeapp003.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircleCheckBox(
    completed: Boolean,
    onChangeState: (Boolean) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(
                color = if (completed)
                    Color(27, 136, 82)
                else
                    Color.White
            )
            .border(
                border = BorderStroke(
                    2.dp,
                    color = if (completed)
                        Color(27, 136, 82)
                    else
                        Color.LightGray
                ),
                shape = CircleShape
            )
            .toggleable(
                value = completed,
                onValueChange = { onChangeState(it) }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (completed) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}