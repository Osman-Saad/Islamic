package com.pbws.islami.commoncomposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold

@Composable
fun TafasierAhadethContent(content:String,title:String) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gold)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()

                .padding(16.dp)
        ) {
            SuraNameTopBar(
                name = title,
                leftSideImg = R.drawable.left_side_shape_dark,
                rightSideImg = R.drawable.right_side_shape_dark,
                titleColor = Black
            )
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.content_bg) ,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth)

                Text(
                    text = content,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.mosque2) ,
            contentDescription =null,
            alignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}
