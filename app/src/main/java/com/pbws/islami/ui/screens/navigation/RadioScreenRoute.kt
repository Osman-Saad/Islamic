package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pbws.islami.ui.screens.radio.RadioScreen

const val RADIO_SCREEN_ROUTE = "radio"
fun NavController.navigateToRadioScreen(){
    navigate(RADIO_SCREEN_ROUTE)
}

fun NavGraphBuilder.radioScreenRoute(navController: NavController){
    composable(
        route = RADIO_SCREEN_ROUTE
    ){
        RadioScreen()
    }
}