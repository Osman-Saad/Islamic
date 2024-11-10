package com.pbws.islami.ui.screens.praytimeazkar.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pbws.islami.ui.screens.commoncomposable.carouselTransition
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.utils.convertToArabicNumerals
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrayTimeItem(prayTime:String,prayNameAr:String,prayNameEn:String,page:Int,pagerState: PagerState){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.carouselTransition(page,pagerState)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Black, Gold),

                        )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),

            ) {
            PrayTimeText(
                text = prayNameAr ,
                color = Color.White,
                fontSize = 15 )
            PrayTimeText(
                text = convertToArabicNumerals(prayTime) ,
                color = Color.White ,
                fontSize = 24)
            PrayTimeText(
                text = prayNameEn ,
                color = Color.White ,
                fontSize = 15)
        }
    }
}


