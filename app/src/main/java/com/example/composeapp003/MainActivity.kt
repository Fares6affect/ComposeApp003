package com.example.composeapp003

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp003.ui.theme.ComposeApp003Theme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApp003Theme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Home(navController)
        }
        composable("competeTask") {
            CompleteTask(navController)
        }
        composable("profile") {
            Profile(navController)
        }
    }
}

@Composable
private fun CircleCheckBox(
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


@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    NavigationBar(
        tonalElevation = 1.dp,
        containerColor = Color.White
    ) {
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.route,
                        tint = if (currentRoute == navItem.route)
                            Color(35, 149, 215)
                        else Color.LightGray
                    )
                },
                label = {
                    Text(
                        navItem.title,
                        style = TextStyle(
                            color = if (currentRoute == navItem.route)
                                Color(35, 149, 215)
                            else Color.LightGray
                        )
                    )
                }
            )
        }
    }
}

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            "Главная",
            Icons.Default.Home,
            "home"
        ),
        BarItem(
            "Завершенные",
            Icons.Default.CheckCircleOutline,
            "competeTask"
        ),
        BarItem(
            "Профиль",
            Icons.Default.Person,
            "profile"
        )
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    val taskList = remember {
        mutableStateListOf<Task>(
            Task("2131", "3242", "32423"),
            Task("213123444364336564564564565464566546446364364", "3223442", "32424353"),
            Task("2131234", "3242234", "324352223")
        )
    }
   Scaffold(
       bottomBar = { BottomNavigationBar(navController) },
       containerColor = Color(233, 239, 246),
       floatingActionButton = {
           FloatingActionButton(
               onClick = {},
               shape = CircleShape,
               elevation = FloatingActionButtonDefaults.elevation(1.dp),
               containerColor = Color(35, 149, 215)
           ) {
               Icon(imageVector = Icons.Filled.Add,
                   contentDescription = "Plus",
                   modifier = Modifier,
                   tint = Color.White)
           }
       },
       floatingActionButtonPosition = FabPosition.End
   ) { innerPadding ->
       Box(
           modifier = Modifier.padding(innerPadding)
       ){
           val buttons = listOf("Все", "Активные", "Завершенные")
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
                   buttons.forEachIndexed { index, string ->
                       val isSelected = (index == selectedIndex)
                       Button(
                           onClick = {
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
                               string,
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
               LazyColumn(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(top = 20.dp),
               ) {
                   items(taskList) { task ->
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
   }
}

@Composable
fun DialogTask(){

}
@Composable
fun CompleteTask( navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){}
    }
}


@Composable
fun Profile(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(1000){
                Text("Письки",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            Color(
                                Random.nextInt(255),
                                Random.nextInt(255),
                                Random.nextInt(255)
                            )
                        ),)
            }
        }
    }
}

/*
ConstraintLayout(

) {
    val (nameProfile,imageProfile) = createRefs()
    createHorizontalChain(nameProfile,imageProfile)
    Text(
        "",
        modifier = Modifier.constrainAs(nameProfile) {
            start.linkTo(parent.start)
        })
    Image(
        painter = ColorPainter(Color.Green),
        contentDescription = "ProfileImage",
        modifier = Modifier.constrainAs(imageProfile){
            end.linkTo(parent.end)
        })
}*/
