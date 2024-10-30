package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.kamali


@Composable
fun IslamicText(){
    Text(
        text = stringResource(R.string.islami),
        fontFamily = kamali,
        color = Gold,
        fontSize = 64.sp
    )
}