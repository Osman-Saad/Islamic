package com.pbws.islami.ui.screens.qurandetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pbws.islami.ui.screens.commoncomposable.TafasierAhadethContent
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold

@Composable
fun QuranTafasirDialog(
    content:String,
    title:String,
    onDismiss:()->Unit,
    onBackIconClick:()->Unit){
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.background(color = Gold)
            ) {
                IconButton(onClick = onBackIconClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Black,
                        modifier = Modifier.align(Alignment.Start)
                        )
                }
                TafasierAhadethContent(content = content , title = title)
            }
        }
    }
}