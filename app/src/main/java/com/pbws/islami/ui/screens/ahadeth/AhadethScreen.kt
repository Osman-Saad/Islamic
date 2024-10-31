package com.pbws.islami.ui.screens.ahadeth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pbws.domain.entity.ahadethentity.AhadethItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.CarouselCard
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.commoncomposable.TafasierAhadethContent
import com.pbws.islami.ui.screens.commoncomposable.carouselTransition
import com.pbws.islami.ui.theme.Gold
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AhadethScreen(viewModel: AhadethViewModel = hiltViewModel()) {

    var ahadeth = viewModel.ahadeth.collectAsLazyPagingItems()
    var loading by remember {
        mutableStateOf(false)
    }
    var loadingFirstTime by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        viewModel.channel.send(AhadethIntent.GetAhadeth)
    }
    LaunchedEffect(Unit) {
        viewModel.state.collect { state ->
            when (state) {
                is AhadethState.Error -> {
                    loading = false
                }

                AhadethState.Ideal -> {}
                AhadethState.Loading -> {
                    if (!loadingFirstTime) {
                        loading = true
                    }
                    loadingFirstTime = true
                }

                is AhadethState.Success -> {
                    loading = false
                }
            }
        }
    }
    AhadethScreenContent(
        loading = loading,
        ahadeth = ahadeth
    )
}

@Composable
fun AhadethScreenContent(
    loading: Boolean,
    ahadeth: LazyPagingItems<AhadethItem>
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.ahadeth_background),
                contentScale = ContentScale.FillBounds
            ),
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 16.dp)
        ) {
            IslamicText()
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (loading) {
                        CircularProgressIndicator(color = Gold)
                    } else {
                        CarouselCard(
                            pagesCount = ahadeth.itemCount,
                            pageSize = PageSize.Fill,
                            padding = 22
                        ) { page, pagerState ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.carouselTransition(page, pagerState)
                            ) {
                                TafasierAhadethContent(
                                    content = ahadeth[page]?.arab ?: "",
                                    title = NumberFormat.getInstance(Locale("ar"))
                                        .format(ahadeth[page]?.number)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

