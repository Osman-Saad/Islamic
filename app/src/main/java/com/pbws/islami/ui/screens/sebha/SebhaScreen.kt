package com.pbws.islami.ui.screens.sebha

import android.icu.text.NumberFormat
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SebhaScreen() {
    val rotationAngle = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    var count by remember { mutableIntStateOf(0) }
    var phasesIndex by remember { mutableIntStateOf(0) }
    var isAnimating by remember { mutableStateOf(false) }
    val currentPhase: String = when (phasesIndex) {
        0 -> stringResource(id = R.string.subhan_allah_label)
        1 -> stringResource(R.string.alhamdulillah_label)
        2 -> stringResource(R.string.allahu_akbar_label)
        else -> ""
    }
    SebhaScreenContent(
        currentPhase = currentPhase,
        counter = count,
        rotationAngle = rotationAngle.value
    ) {
        if(!isAnimating){
            isAnimating  = true
            coroutineScope.launch {
                rotationAngle.stop()
                rotationAngle.animateTo(
                    targetValue = rotationAngle.value + (360f / 33f),
                    animationSpec = tween(durationMillis = 300)
                )
                count += 1
                if (count == 33) {
                    count = 0
                    when (phasesIndex) {
                        0 -> phasesIndex = 1
                        1 -> phasesIndex = 2
                        2 -> phasesIndex = 0
                    }
                    coroutineScope.launch {
                        rotationAngle.animateTo(0f, animationSpec = tween(durationMillis = 20))
                    }
                }
                isAnimating = false
            }
        }
    }
}

@Composable
fun SebhaScreenContent(
    currentPhase: String,
    counter: Int,
    rotationAngle: Float,
    onTasabehClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.sebha_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            IslamicText()
            SebhaText(text = stringResource(id = R.string.sebha_title))
            Image(
                painter = painterResource(id = R.drawable.sebha),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight(0.4f)
                    .rotate(rotationAngle)
            )
            SebhaText(
                text = "$currentPhase\n${NumberFormat.getInstance(Locale("ar")).format(counter)}",
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onTasabehClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gold,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Text(
                    text = "$currentPhase",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun SebhaText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontFamily = jannaLt,
        color = Color.White,
        modifier = modifier
            .padding(vertical = 16.dp),
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
    )
}