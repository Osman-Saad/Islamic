package com.pbws.islami.ui.screens.quran.compsable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.domain.entity.quranentity.SuwarItem
import com.pbws.islami.R
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun SuraItem(sura:SuwarItem,modifier: Modifier,onItemClick:(Int)->Unit) {
    Column(modifier = modifier.clickable(onClick = {
        onItemClick(sura?.id?:0)
    })) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.quran_number_ic),
                    contentDescription = null,
                )
                Text(
                    text = sura.id.toString(),
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if(sura.makkia==1) stringResource(R.string.makia_label) else stringResource(R.string.madania_label),
                fontFamily = jannaLt,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = sura.name?:"",
                fontFamily = jannaLt,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White,
        )
    }

}