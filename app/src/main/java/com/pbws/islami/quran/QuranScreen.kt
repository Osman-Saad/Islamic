package com.pbws.islami.quran

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.commoncomposable.IslamicText
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt

@Composable
//@Preview(showSystemUi = true)
fun QuranScreen() {
    QuranScreenContent()
}

@Composable
fun QuranScreenContent() {
    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.quran_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp)
        ) {
            IslamicText()
            OutlinedTextField(
                value = textFieldValue ,
                onValueChange = {textFieldValue = it},
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "اسم السوره",
                        color = Color.White,
                        fontFamily = jannaLt,
                        fontSize = 12.sp,
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontFamily = jannaLt,
                    fontSize = 18.sp,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.quran_ic) ,
                        contentDescription = null,
                        tint = Gold)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White,
                    errorTextColor = Color.White,
                    unfocusedBorderColor = Gold,
                    focusedBorderColor = Gold,
                    errorBorderColor = Gold,
                    disabledBorderColor = Gold,
                    cursorColor = Gold,
                    focusedContainerColor = AlphaBlack,
                    unfocusedContainerColor = AlphaBlack,
                    disabledContainerColor = AlphaBlack
                )
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SuraListText(text = "Sura List")
                SuraListText(text = "قائمه السور")
            }
            LazyColumn {
                items(20){
                    SuraItem()
                }
            }
        }
    }
}

@Composable
fun SuraListText(text:String){
    Text(
        text = text,
        fontFamily = jannaLt,
        color = Color.White,
        fontSize = 16.sp,
    )
}


@Composable
fun SuraItem() {
    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.quran_number_ic),
                    contentDescription = null,
                )
                Text(
                    text = "25",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Al-Fatha",
                    fontFamily = jannaLt,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "7 Verses",
                    fontFamily = jannaLt,
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "الفاتحه",
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

@Composable
@Preview(showSystemUi = true)
fun AppPreview(){
    QuranScreenContent()
}

