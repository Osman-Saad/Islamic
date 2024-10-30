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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pbws.domain.entity.qurandetailsentity.AyahsItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.qurandetails.QuranTafasirDialog
import com.pbws.islami.ui.screens.qurandetails.composable.AyaItem
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt

@Composable
fun QuranDetailsContent(
    navController: NavController,
    showDialog:Boolean,
    dialogContent:String,
    loading:Boolean,suraNameAr:String,
    suraNameEN: String,ayat:List<AyahsItem>,
    onAyaClick:(Int)->Unit,
    onDialogDismiss:()->Unit,
    onDialogBackIconClick:()->Unit
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
                            text = suraNameEN,
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
                        name = suraNameAr,
                        leftSideImg = R.drawable.left_side_shape,
                        rightSideImg = R.drawable.right_side_shape,
                        titleColor = Gold
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(ayat){
                        AyaItem(it, onAyaClick = {
                            if(it>0){
                                onAyaClick(it-1)
                            }
                        })
                    }
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
    if(showDialog) {
        QuranTafasirDialog(
            content = dialogContent,
            title = stringResource(R.string.tafsir_title),
            onDismiss = onDialogDismiss,
            onBackIconClick = onDialogBackIconClick
        )
    }
}
