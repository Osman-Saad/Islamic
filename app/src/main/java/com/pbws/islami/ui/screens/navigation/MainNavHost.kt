package com.pbws.islami.ui.screens.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainNavHost(navHostController: NavHostController,modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = QURAN_SCREEN_ROUTE,
        modifier = Modifier
    ) {
        quranScreenRoute(navHostController)
        quranDetailsScreen(navHostController)
        ahadethScreenRoute(navHostController)
        sephaScreenRoute(navHostController)
        radioScreenRoute(navHostController)
        prayTimeScreenRoute(navHostController)
        azkarDetailsScreen(navHostController)
    }
}