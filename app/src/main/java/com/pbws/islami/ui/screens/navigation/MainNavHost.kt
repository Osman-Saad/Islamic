package com.pbws.islami.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

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
    }
}