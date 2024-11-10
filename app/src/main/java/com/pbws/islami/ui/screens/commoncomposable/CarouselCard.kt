package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(pagesCount:Int, pageSize:PageSize = PageSize.Fixed(100.dp),padding:Int = 130,  content: @Composable (Int,PagerState) -> Unit) {
    val pagerState = rememberPagerState(
        pageCount = { pagesCount },
        initialPage = pagesCount/2
    )

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        pageSize = pageSize,
        contentPadding = PaddingValues(horizontal = padding.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) { page -> 
        content(page,pagerState)
    }
}




