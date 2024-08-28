package com.pbws.islami.sebha

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.commoncomposable.IslamicText
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt

@Preview(showSystemUi = true)
@Composable
fun SebhaScreen() {
    SebhaScreenContent()
}

@Composable
fun SebhaScreenContent(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.sebha_background),
                contentScale = ContentScale.FillBounds
            )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            IslamicText()
            SebhaText(text = R.string.sebha_title)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sebha) ,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                    )
               SebhaText(
                   text = R.string.allah_akbar_text,
                   modifier = Modifier.padding(top = 48.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gold,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.allah_akbar_text),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                    )
            }
        }
    }
}

@Composable
fun SebhaText(text:Int,modifier: Modifier = Modifier) {
    Text(
        text = stringResource(text),
        fontFamily = jannaLt,
        color = Color.White,
        modifier = modifier
            .padding(vertical = 16.dp),
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        )
}