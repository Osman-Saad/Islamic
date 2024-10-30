package com.pbws.islami.ui.screens.commoncomposable.mainscreen

import com.pbws.islami.R
import com.pbws.islami.ui.screens.navigation.AHADETH_SCREEN_ROUTE
import com.pbws.islami.ui.screens.navigation.PRAY_TIME_SCREEN_ROUTE
import com.pbws.islami.ui.screens.navigation.QURAN_SCREEN_ROUTE
import com.pbws.islami.ui.screens.navigation.RADIO_SCREEN_ROUTE
import com.pbws.islami.ui.screens.navigation.Sepha_SCREEN_ROUTE

sealed class BottomBarsScreen(
    val route:String,
    val icon:Int,
    val label:String
) {
    data object QuranScreen:BottomBarsScreen(
        route = QURAN_SCREEN_ROUTE,
        icon = R.drawable.quran_ic,
        label = "قرأن"
    )
    data object AhadethScreen:BottomBarsScreen(
        route = AHADETH_SCREEN_ROUTE,
        icon = R.drawable.ahadeth_ic,
        label = "أحاديث"
    )
    data object SephaScreen:BottomBarsScreen(
        route = Sepha_SCREEN_ROUTE,
        icon = R.drawable.sebha_ic,
        label = "السبحه"
    )
    data object RadioScreen:BottomBarsScreen(
        route = RADIO_SCREEN_ROUTE,
        icon = R.drawable.radio_ic,
        label = "ألراديو"
    )
    data object PrayTimeScreen:BottomBarsScreen(
        route = PRAY_TIME_SCREEN_ROUTE,
        icon = R.drawable.time_ic,
        label = "مواقيت الصلاه"
    )


}