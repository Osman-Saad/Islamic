package com.pbws.islami.ui.screens.qurandetails


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pbws.domain.entity.qurandetailsentity.AyahsItem
import com.pbws.domain.entity.qurantafasirentity.QuranTafasirItem
import com.pbws.islami.ui.screens.commoncomposable.QuranDetailsContent


@Composable
fun QuranDetailsScreen(navController: NavController,suraNumber:Int,viewModel: QuranDetailsViewModel = hiltViewModel()) {
    var state = viewModel.state
    var loading by remember {
        mutableStateOf(false)
    }
    var ayat by remember {
        mutableStateOf<List<AyahsItem>>(emptyList())
    }
    var ayatTafasir by remember {
        mutableStateOf<List<QuranTafasirItem>>(emptyList())
    }
    var suraNameAr by remember {
        mutableStateOf("")
    }
    var suraNameEN by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var ayaIndexClicked by remember {
        mutableIntStateOf(-1)
    }
    LaunchedEffect(Unit) {
        viewModel.channel.send(QuranDetailsIntent.GetQuranDetails(suraNumber))
    }
    LaunchedEffect(Unit) {
        state.collect { state ->
            when (state) {
                is QuranDetailsState.Error -> {
                    loading = false
                }

                is QuranDetailsState.Ideal -> {}
                is QuranDetailsState.Loading -> {
                    loading = true
                }

                is QuranDetailsState.Success -> {
                    loading = false
                    state.quranDetails.data?.let {
                        ayat = state.quranDetails.data?.ayahs?.filterNotNull() ?: emptyList()
                        suraNameAr = state.quranDetails.data?.name ?: ""
                        suraNameEN = state.quranDetails.data?.englishName ?: ""
                    }
                    state.quranTafasir.result?.let {
                        ayatTafasir = it.filterNotNull()
                        Log.d("TS",ayatTafasir.toString())
                    }
                }
            }
        }
    }

    QuranDetailsContent(
        navController = navController,
        loading = loading,
        suraNameAr = suraNameAr,
        suraNameEN = suraNameEN,
        ayat = ayat,
        dialogContent = if(ayaIndexClicked==-1)"" else "${ayatTafasir[ayaIndexClicked].arabic_text}\n***********\n${ayatTafasir[ayaIndexClicked].translation?:""}",
        showDialog = showDialog,
        onAyaClick = {
            ayaIndexClicked  = it
            showDialog = true
        },
        onDialogDismiss = {
            showDialog = false
        },
        onDialogBackIconClick = {
            showDialog = false
        }
    )
}



