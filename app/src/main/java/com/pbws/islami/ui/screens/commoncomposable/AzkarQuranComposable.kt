package com.pbws.islami.ui.screens.commoncomposable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pbws.islami.R
import com.pbws.islami.ui.screens.qurandetails.composable.AyaItem
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun AzkarQuranComposable(
    navController: NavController,
    loading:Boolean,
    titleAr:String,
    titleEn:String,
    lazyColumnComposable: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = loading) {
            CircularProgressIndicator(color = Gold, modifier = Modifier.align(Alignment.Center))
        }
        AnimatedVisibility(visible = !loading) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Gold,
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(color = Color.Transparent),
                                onClick = {
                                    navController.navigateUp()
                                }
                            )
                        )
                        Text(
                            text = titleEn,
                            fontSize = 18.sp,
                            fontFamily = jannaLt,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            color = Gold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    SuraNameTopBar(
                        name = titleAr,
                        leftSideImg = R.drawable.left_side_shape,
                        rightSideImg = R.drawable.right_side_shape,
                        titleColor = Gold
                    )
                }
                Box(modifier = Modifier.weight(1f)){
                    lazyColumnComposable()
                }
                Image(
                    painter = painterResource(id = R.drawable.mosque),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}