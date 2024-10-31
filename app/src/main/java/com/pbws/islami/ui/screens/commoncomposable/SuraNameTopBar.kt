package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun SuraNameTopBar(
    leftSideImg:Int,
    rightSideImg:Int,
    titleColor:Color,
    name:String
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = leftSideImg),
            contentDescription = null,
            modifier = Modifier.size(84.dp)
        )
        Text(
            text = name,
            fontSize = 24.sp,
            fontFamily = jannaLt,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = titleColor,
        )
        Image(
            painter = painterResource(id = rightSideImg),
            contentDescription = null,
            modifier = Modifier.size(84.dp)
        )
    }
}
