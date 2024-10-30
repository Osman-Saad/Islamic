package com.pbws.islami.ui.screens.quran.compsable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun SuraListText(text: String) {
    Text(
        text = text,
        fontFamily = jannaLt,
        color = Color.White,
        fontSize = 16.sp,
    )
}