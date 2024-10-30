package com.pbws.islami.ui.screens.qurandetails.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.domain.entity.qurandetailsentity.AyahsItem
import com.pbws.islami.R
import com.pbws.islami.ui.theme.Gold

@Composable
fun AyaItem(ayahsItem: AyahsItem,onAyaClick:(Int)->Unit) {

    val iconId = R.drawable.quran_number_ic
    val iconSize = 32.dp

    val textWithIcon = buildAnnotatedString {
        append(ayahsItem.text)
        append("  ")
        appendInlineContent("icon", "[icon]")
    }

    val inlineContent = mapOf(
        "icon" to InlineTextContent(
            Placeholder(
                width = 48.sp,
                height = 48.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    tint = Gold,
                )
                Text(
                    text = ayahsItem.numberInSurah.toString(),
                    color = Gold,

                    )
            }
        }
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = 1.dp, color = Gold, shape = RoundedCornerShape(8.dp))
                .clickable(onClick = {onAyaClick(ayahsItem.numberInSurah?:0)})
        ) {
            Text(
                text = textWithIcon,
                fontSize = 22.sp,
                color = Gold,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(textDirection = TextDirection.Rtl),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                inlineContent = inlineContent
            )
        }
    }
}
