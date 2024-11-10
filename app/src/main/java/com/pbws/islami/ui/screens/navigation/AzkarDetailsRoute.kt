package com.pbws.islami.ui.screens.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pbws.islami.ui.screens.praytimeazkar.azkar.AzkarDetailsScreen

private const val AZKAR_DETAILS_ROUTE = "azkar_details"
private const val AZKAR_DETAILS_ARGUMENT = "azkar_type"
private const val azkarDetailsRoute = "$AZKAR_DETAILS_ROUTE/{$AZKAR_DETAILS_ARGUMENT}"

fun NavController.navigateToAzkarDetailsScreen(azkarType: String) {
    navigate("$AZKAR_DETAILS_ROUTE/$azkarType")
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun NavGraphBuilder.azkarDetailsScreen(navController: NavController) {
    composable(
        route = azkarDetailsRoute,
        arguments = listOf(
            navArgument(AZKAR_DETAILS_ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val azkarType = navBackStackEntry.arguments?.getString(AZKAR_DETAILS_ARGUMENT) ?: ""
       AzkarDetailsScreen(azkarType = azkarType, navController =navController )
    }
}