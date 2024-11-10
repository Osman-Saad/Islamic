package com.pbws.islami.ui.screens.commoncomposable.mainscreen

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pbws.islami.ui.screens.navigation.MainNavHost
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Gold


@Composable
fun MainScreen(){
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Gold,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Gold,
            darkIcons = false
        )
    }
    MainScreenContent()
}

@Composable
fun MainScreenContent() {

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {innerPadding->
        Box(
          modifier = Modifier.padding(innerPadding)
        ){
            MainNavHost(
                navHostController = navController,
                modifier = Modifier)
        }
    }
}

@Composable
fun BottomBar(navController: NavController){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val screens = listOf<BottomBarsScreen>(
        BottomBarsScreen.QuranScreen,
        BottomBarsScreen.AhadethScreen,
        BottomBarsScreen.SephaScreen,
        BottomBarsScreen.RadioScreen,
        BottomBarsScreen.PrayTimeScreen
    )
    BottomNavigation(
        backgroundColor = Gold,
        modifier = Modifier.navigationBarsPadding()
    ){
        screens.forEach {
            AddBottomBArItem(
                screen = it,
                navController = navController,
                currentDestination = currentDestination)
        }
    }
}
@Composable
fun BottomBar2(navController: NavController){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val screens = listOf<BottomBarsScreen>(
        BottomBarsScreen.QuranScreen,
        BottomBarsScreen.AhadethScreen,
        BottomBarsScreen.SephaScreen,
        BottomBarsScreen.RadioScreen,
        BottomBarsScreen.PrayTimeScreen
    )
    NavigationBar() {
        screens.forEach {
            AddBottomBArItem(
                screen =it ,
                navController = navController ,
                currentDestination = currentDestination)
        }

    }
}

@Composable
fun RowScope.AddBottomBArItem(
    screen: BottomBarsScreen,
    navController: NavController,
    currentDestination: NavDestination?
){
    BottomNavigationItem(

        label = {
            Text(
                text = screen.label,
                maxLines = 1,
                minLines = 1
                )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            if(currentDestination?.hierarchy?.any { it.route == screen.route } != true) {
                navController.navigate(
                    screen.route
                ){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        },
        alwaysShowLabel = false,
       selectedContentColor = Color.White,
        unselectedContentColor = Color.Black,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = null,
                tint = if(currentDestination?.hierarchy?.any { it.route == screen.route } == true) Color.White else Color.Black
            )
        })
}
