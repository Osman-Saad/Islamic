package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pbws.islami.ui.screens.quran.QuranScreen

const val QURAN_SCREEN_ROUTE = "quran"

fun NavController.navigateToQuranScreen(){
    navigate(QURAN_SCREEN_ROUTE)
}

fun NavGraphBuilder.quranScreenRoute(navController: NavController){
   composable(
       route = QURAN_SCREEN_ROUTE
   ){
       QuranScreen(navController)
   }
}