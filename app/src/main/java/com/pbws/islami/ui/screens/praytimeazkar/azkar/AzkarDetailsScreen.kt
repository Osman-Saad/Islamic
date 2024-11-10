package com.pbws.islami.ui.screens.praytimeazkar.azkar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pbws.data.model.azkar.masaa.AzkarMassaItem
import com.pbws.data.model.azkar.sabah.AzkarSabahItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.AzkarQuranComposable

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AzkarDetailsScreen(
    azkarType: String,
    navController: NavController,
    viewModel: AzkarViewModel = hiltViewModel()
) {
    var loading by remember {
        mutableStateOf(false)
    }
    var azkarElsabahItemList by remember {
        mutableStateOf(listOf<AzkarSabahItem>())
    }
    var azkarElmasaaList by remember {
        mutableStateOf(listOf<AzkarMassaItem>())
    }

    LaunchedEffect(Unit) {
        if(azkarType == AzkarType.AZKAR_ELSABAH.name) {
            viewModel.channel.send(AzkarIntent.GetAzkarSabah)
        } else {
            viewModel.channel.send(AzkarIntent.GetAzkarMassaa)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.state.collect { state ->
            when (state) {
                is AzkarState.Error -> {
                    loading = false
                }

                is AzkarState.Ideal -> {}
                is AzkarState.Loading -> {
                    loading = true
                }

                is AzkarState.Success -> {
                    loading = false
                    if (azkarType == AzkarType.AZKAR_ELSABAH.name) {
                        azkarElsabahItemList = state.azkar as List<AzkarSabahItem>
                    } else {
                        azkarElmasaaList = state.azkar as List<AzkarMassaItem>
                    }
                }
            }
        }
    }

    AzkarDetailsContent(
        navController = navController,
        loading = loading,
        title = if (azkarType == AzkarType.AZKAR_ELSABAH.name) stringResource(id = R.string.azkar_sabah) else stringResource(
            id = R.string.evenening_azkar
        ),
        azkarType = azkarType,
        azkarList = if (azkarType == AzkarType.AZKAR_ELSABAH.name) azkarElsabahItemList else azkarElmasaaList,
    )
}

@Composable
fun AzkarDetailsContent(
    navController: NavController,
    loading: Boolean,
    title: String,
    azkarType: String,
    azkarList: List<Any>
) {
    AzkarQuranComposable(
        navController = navController,
        loading = loading,
        titleAr = title,
        titleEn = title
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(azkarList) {
                Log.d("AZ",azkarList.toString())
                AzkarItem(
                    azkar = it,
                    azkatype = azkarType,
                    azkarNumber = azkarList.indexOf(it) + 1
                )
            }
        }
    }
}
