package com.pbws.islami.ui.screens.praytime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.CarouselCard
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.praytime.composable.AzkarCard
import com.pbws.islami.ui.screens.praytime.composable.PrayTimeText
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Brown




@Preview(showSystemUi = true)
@Composable
fun TimeScreen(){
    TimeScreenContent()
}





@Composable
fun TimeScreenContent() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.time_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        IslamicText()
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(color = Brown, shape = RoundedCornerShape(50.dp))
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.time_card_shape) ,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
                )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrayTimeText(text = "28 Aug\n2024", color = Color.White, fontSize = 16 )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        PrayTimeText(text = "مواقيت الصلاه", color = Brown, fontSize = 20 )
                        PrayTimeText(text = "الاربعاء", color = Black, fontSize = 18 )
                    }
                    PrayTimeText(text = "22 Ṣafar\n1446", color = Color.White, fontSize = 16 )

                }
                CarouselCard()
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,fontSize = 16.sp,color = Brown)) {
                            append(stringResource(R.string.next_pray))
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Black,fontSize = 16.sp)) {
                            append(" 2:45 ")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrayTimeText(
                text = stringResource(R.string.azkar_en) ,
                color = Color.White,
                fontSize = 16)

            PrayTimeText(
                text = stringResource(R.string.azkar_ar) ,
                color = Color.White,
                fontSize = 16)
        }
        Row(modifier = Modifier
            .padding(bottom = 24.dp)
            .fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                AzkarCard(image = R.drawable.mornning_azkar, title = stringResource(R.string.azkar_sabah), onClick = {})
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                AzkarCard(image = R.drawable.evening_azkar, title = stringResource(R.string.evenening_azkar), onClick = {})
            }
        }

    }

}


