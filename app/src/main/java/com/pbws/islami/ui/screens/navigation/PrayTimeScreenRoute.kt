package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pbws.islami.ui.screens.praytime.TimeScreen

const val PRAY_TIME_SCREEN_ROUTE = "pry_time"
fun NavController.navigateToPryTimeScreen(){
    navigate(PRAY_TIME_SCREEN_ROUTE)
}

fun NavGraphBuilder.prayTimeScreenRoute(navController: NavController){
    composable(
        route = PRAY_TIME_SCREEN_ROUTE
    ){
        TimeScreen()
    }
}