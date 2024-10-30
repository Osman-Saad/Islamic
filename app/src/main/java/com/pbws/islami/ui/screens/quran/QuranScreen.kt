package com.pbws.islami.ui.screens.quran

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pbws.domain.entity.quranentity.SuwarItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.navigation.navigateToQuranDetailsScreen
import com.pbws.islami.ui.screens.quran.compsable.SuraItem
import com.pbws.islami.ui.screens.quran.compsable.SuraListText
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt
import kotlinx.coroutines.flow.collect

@Composable
//@Preview(showSystemUi = true)
fun QuranScreen(navController: NavController,viewModel: QuranViewModel = hiltViewModel()) {
    val state = viewModel.state
    var loading by remember {
        mutableStateOf(false)
    }
    var quranList by rememberSaveable {
        mutableStateOf<List<SuwarItem>>(emptyList())
    }
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    var quranListcopy by rememberSaveable {
        mutableStateOf<List<SuwarItem>>(emptyList())
    }

    LaunchedEffect(Unit) {
        viewModel.channel.send(QuranIntent.GetQuran)
    }
    LaunchedEffect(Unit) {
        state.collect{state->
            when(state){
                is QuranState.Error -> {loading = false}
                QuranState.Ideal -> {}
                QuranState.Loading -> {loading = true}
                is QuranState.Success ->{
                    loading = false
                    quranList = state.quranItems
                    quranListcopy = state.quranItems
                }
            }
        }
    }
    QuranScreenContent(
        quranList = quranList,
        loading = loading,
        searchValue = searchValue,
        onItemClick = {
            navController.navigateToQuranDetailsScreen(it)
        },
        onSearchValueChanged = {value->
            searchValue = value

            if(value.isNotEmpty()){
                val searchedList = quranListcopy.filter {
                    it.name?.contains(value) ?: false
                }
                quranList = searchedList
            }else{
                quranList = quranListcopy
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuranScreenContent(
    quranList: List<SuwarItem>,
    loading: Boolean,
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    onItemClick:(Int)->Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.quran_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp)
        ) {
            IslamicText()
            OutlinedTextField(
                value = searchValue,
                onValueChange = { onSearchValueChanged(it) },
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.sura_name_placeholder),
                        color = Color.White,
                        fontFamily = jannaLt,
                        fontSize = 12.sp,
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontFamily = jannaLt,
                    fontSize = 18.sp,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.quran_ic),
                        contentDescription = null,
                        tint = Gold
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White,
                    errorTextColor = Color.White,
                    unfocusedBorderColor = Gold,
                    focusedBorderColor = Gold,
                    errorBorderColor = Gold,
                    disabledBorderColor = Gold,
                    cursorColor = Gold,
                    focusedContainerColor = AlphaBlack,
                    unfocusedContainerColor = AlphaBlack,
                    disabledContainerColor = AlphaBlack
                )
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SuraListText(text = stringResource(R.string.sura_list_label))
                SuraListText(text = stringResource(R.string.sura_list_label_ar))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility(loading) {
                    CircularProgressIndicator(color = Gold)
                }
                androidx.compose.animation.AnimatedVisibility(!loading) {
                    LazyColumn(
                       modifier = Modifier.fillMaxSize()
                    ) {
                        items(quranList) {
                            SuraItem(it, modifier = Modifier.animateItemPlacement(), onItemClick ={
                                onItemClick(it)
                            } )
                        }
                    }
                }
            }
        }
    }
}








