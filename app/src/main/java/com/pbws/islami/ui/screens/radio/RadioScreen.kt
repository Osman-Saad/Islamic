package com.pbws.islami.ui.screens.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.commoncomposable.SoundItem
import com.pbws.islami.ui.screens.commoncomposable.TabLayout

@Preview(showSystemUi = true)
@Composable
fun RadioScreen() {
    RadioScreenContent()
}

@Composable
fun RadioScreenContent() {
    var tabSelectedIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var tabsList = listOf(
        stringResource(R.string.radio_tab_text),
        stringResource(R.string.quran_tab_text)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.radio_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            IslamicText()
            TabLayout(tabs = tabsList)
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(20){
                    SoundItem()
                }
            }
        }
    }
}




