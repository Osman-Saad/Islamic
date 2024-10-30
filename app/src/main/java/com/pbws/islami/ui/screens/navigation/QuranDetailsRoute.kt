package com.pbws.islami.ui.screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pbws.islami.ui.screens.qurandetails.QuranDetailsScreen

const val QURAN_DETAILS_ROUTE = "quran_details"
private const val SURA_NUMBER_ARGS = "sura_number"

fun NavController.navigateToQuranDetailsScreen(suraNumber:Int) {
    navigate("$QURAN_DETAILS_ROUTE/$suraNumber")
}

fun NavGraphBuilder.quranDetailsScreen(navController:NavController) {
    composable(
        route = "$QURAN_DETAILS_ROUTE/{$SURA_NUMBER_ARGS}",
        arguments = listOf(
            navArgument(SURA_NUMBER_ARGS) {
                type = NavType.IntType
            }
        )
    ) {navBackStackEntry->
        val suraNumber = navBackStackEntry.arguments?.getInt(SURA_NUMBER_ARGS) ?: 0
        QuranDetailsScreen(navController,suraNumber)
    }
}