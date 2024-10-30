package com.pbws.islami.ui.screens.praytime.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pbws.islami.ui.theme.Gold

@Composable
fun AzkarCard(image:Int,title:String,onClick:()->Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .border(color = Gold, width = 1.dp, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = image) ,
                contentDescription =null,
                modifier = Modifier.size(150.dp))
            PrayTimeText(
                text = title,
                color = Color.White,
                fontSize = 20)
        }
    }
}