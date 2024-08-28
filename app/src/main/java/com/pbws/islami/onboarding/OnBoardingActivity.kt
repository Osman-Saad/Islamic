package com.pbws.islami.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pbws.islami.R
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            OnBoardingScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    var pagerState = rememberPagerState(pageCount = {
        5
    })
    val coroutineScope = rememberCoroutineScope()
    var enabled by remember { mutableStateOf(false) }

    val indicatorSize: Dp by animateDpAsState(if (enabled) 4.dp else 2.dp, label = "indicatorSize")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.onboarding_bg),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalPager(
                pageSpacing = 16.dp,
                contentPadding = PaddingValues(horizontal = 16.dp),
                state = pagerState) {
                when (it) {
                    0 -> OnBoardingFirstScreenContent()
                    1 -> OnBoardingContent(
                        imageId = R.drawable.onboarding_secound,
                        title = stringResource(R.string.onboarding_second_title),
                        subTitle = stringResource(R.string.onboarding_second_label)
                    )

                    2 -> OnBoardingContent(
                        imageId = R.drawable.onboarding_third,
                        title = stringResource(R.string.onboarding_third_title),
                        subTitle = stringResource(R.string.onboarding_third_label)
                    )

                    3 -> OnBoardingContent(
                        imageId = R.drawable.onboarding_fourth,
                        title = stringResource(R.string.onboarding_fourth_title),
                        subTitle = stringResource(R.string.onboarding_fourth_label)
                    )

                    4 -> OnBoardingContent(
                        imageId = R.drawable.onboarding_secound,
                        title = stringResource(R.string.onboarding_fifth_title),
                        subTitle = stringResource(R.string.onboarding_fifth_label)
                    )

                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OnBoardingAction(
                    label = stringResource(R.string.back_label),
                    onActionClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage.minus(1))
                        }
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Gold else Color.DarkGray
                        Box(
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 2.dp)
                                .clip(CircleShape)
                                .background(color = color)
                                .size(16.dp)
                        )
                    }
                }
                if(pagerState.canScrollForward){
                    OnBoardingAction(
                        label = stringResource(R.string.next_label),
                        onActionClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage.plus(1))
                            }
                        }
                    )
                }else{
                    OnBoardingAction(
                        label = stringResource(R.string.go_label),
                        onActionClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage.plus(1))
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun OnBoardingAction(label:String,onActionClick:()->Unit){
    TextButton(onClick = onActionClick ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Gold,
            fontFamily = jannaLt,
            fontWeight = FontWeight.Bold
            )
    }
}

