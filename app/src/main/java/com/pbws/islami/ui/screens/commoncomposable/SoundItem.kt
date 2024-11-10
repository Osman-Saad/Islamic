package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Brown
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun SoundItem(
    title:String,
    isPlaying:Boolean,
    onOpenSoundClick:(Boolean)->Unit,
    onForwardClick:()->Unit,
    onBackwardClick:()->Unit
    ){
    var audioIsPlay by rememberSaveable {
        mutableStateOf(false)
    }

    if (audioIsPlay != isPlaying) {
        audioIsPlay = isPlaying
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(
            color = Gold,
            shape = RoundedCornerShape(18.dp)
        )
    ){
        Column {
            Text(
                text = title,
                fontSize = 22.sp,
                fontFamily = jannaLt,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold,
                color = Brown,
                textAlign = TextAlign.Center
            )
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.mosque2) ,
                    contentDescription =null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {onBackwardClick()}) {
                        Icon(
                            painter = painterResource(id = R.drawable.previous_sound_ic) ,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(onClick = {
                        audioIsPlay = !audioIsPlay
                        onOpenSoundClick(audioIsPlay)
                    }) {
                        Icon(
                            painter = painterResource(id = if(audioIsPlay) R.drawable.open_sound else R.drawable.play_ic) ,
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(onClick = { onForwardClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.next_sound_ic) ,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        }
    }
}