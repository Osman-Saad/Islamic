package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pbws.islami.ui.screens.praytime.composable.PrayTimeItem

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true)
@Composable
fun CarouselCard() {
    val pagerState = rememberPagerState(
        pageCount = { 6 },
        initialPage = 3
    )

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        pageSize = PageSize.Fixed(100.dp),
        contentPadding = PaddingValues(horizontal = 130.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) { page ->
        PrayTimeItem(page = page, pagerState = pagerState)
    }
}




