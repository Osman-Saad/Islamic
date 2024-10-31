package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pbws.islami.ui.screens.ahadeth.AhadethScreen
import com.pbws.islami.ui.screens.sebha.SebhaScreen

const val Sepha_SCREEN_ROUTE = "sepha"
fun NavController.navigateToSephaScreen(){
    navigate(Sepha_SCREEN_ROUTE)
}

fun NavGraphBuilder.sephaScreenRoute(navController: NavController){
    composable(
        route = Sepha_SCREEN_ROUTE
    ){
        SebhaScreen()
    }
}