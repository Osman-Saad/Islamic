package com.pbws.islami.ui.screens.praytime.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun PrayTimeText(text:String, color: Color, fontSize:Int){
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        fontFamily = jannaLt,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    )
}