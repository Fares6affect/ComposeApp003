package com.example.composeapp003.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composeapp003.Task
import com.example.composeapp003.TypeVisTask
import com.example.composeapp003.core.ui.components.ListTask
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val allTask = remember {
        mutableStateListOf<Task>(
            Task("2131", "3242", "32423"),
            Task("213123444364336564564564565464566546446364364", "3223442", "32424353"),
            Task("2131234", "3242234", "324352223")
        )
    }
    var showDialog by remember { mutableStateOf(false) }
    var typeVisTask by remember { mutableStateOf(TypeVisTask.ALL_TASK) }

    Box{
        var selectedIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "Привет!\uD83D\uDC4B",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                    Text(
                        "Сегодня отличное время для новых достижений",
                        style = TextStyle(
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "settings"
                        )
                    }
                }
            }

            val searchBarState = rememberSearchBarState()
            val textFieldState = rememberTextFieldState()
            val scope = rememberCoroutineScope()
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White,
                    dividerColor = Color.Transparent,
                    inputFieldColors = SearchBarDefaults.inputFieldColors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedLeadingIconColor = Color.Black,
                        unfocusedLeadingIconColor = Color.Gray
                    )
                ),
                state = searchBarState,
                inputField = @Composable {
                    SearchBarDefaults.InputField(
                        textFieldState = textFieldState,
                        searchBarState = searchBarState,
                        onSearch = {
                            scope.launch { searchBarState.animateToCollapsed() }
                        },
                        placeholder = { Text("Поиск задач...") },
                        leadingIcon = {
                            if (searchBarState.currentValue == SearchBarValue.Expanded) {
                                IconButton(onClick = { scope.launch { searchBarState.animateToCollapsed() } }) {
                                    Icon(Icons.AutoMirrored.Default.ArrowBack, "Назад")
                                }
                            } else {
                                Icon(Icons.Default.Search, null)
                            }
                        }
                    )
                }
            )

            Text(
                "Мои задачи",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                ),
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TypesTasks.buttons.forEachIndexed { index, task ->
                    val isSelected = (index == selectedIndex)
                    Button(
                        onClick = {
                            task.onClick()
                            selectedIndex = index
                        },
                        colors = ButtonDefaults.buttonColors(
                            if (isSelected)
                                Color(35, 149, 215)
                            else
                                Color.White
                        )
                    ) {
                        Text(
                            task.type,
                            style = TextStyle(
                                color =
                                    if (isSelected)
                                        Color.White
                                    else
                                        Color.Black
                            )
                        )
                    }
                }
            }

            ListTask(allTask, typeVisTask.onChoise)

            if (showDialog) {
                Dialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    properties = DialogProperties()
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        DialogTask(
                            onConfirm = { task, day, time ->
                                allTask.add(Task(task, day, time))
                                showDialog = false
                            },
                            onCancel = {
                                showDialog = false
                            }
                        )
                    }
                }
            }
        }
    }

}