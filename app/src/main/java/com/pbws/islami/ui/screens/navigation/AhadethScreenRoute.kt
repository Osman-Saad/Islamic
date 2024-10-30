package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pbws.islami.ui.screens.ahadeth.AhadethScreen

const val AHADETH_SCREEN_ROUTE = "ahadeth"

fun NavController.navigateToAhadethScreen(){
    navigate(AHADETH_SCREEN_ROUTE)
}

fun NavGraphBuilder.ahadethScreenRoute(navController: NavController){
    composable(
        route = AHADETH_SCREEN_ROUTE
    ){
        AhadethScreen()
    }
}