package com.pbws.islami.ui.screens.quran

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pbws.domain.entity.quranentity.SuwarItem
import com.pbws.islami.R
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.navigation.navigateToQuranDetailsScreen
import com.pbws.islami.ui.screens.quran.compsable.SuraItem
import com.pbws.islami.ui.screens.quran.compsable.SuraListText
import com.pbws.islami.ui.screens.splash.suwarList
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.ui.theme.jannaLt
import com.pbws.islami.utils.GpsStatusReceiver
import com.pbws.islami.utils.gpsTracker
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QuranScreen(navController: NavController, viewModel: QuranViewModel = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current
    val activity = context as Activity

    val locationManager = getSystemService(activity!!, LocationManager::class.java)
    var isGpsEnabled by remember {
        mutableStateOf(locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true)
    }
    var requestGps by remember {
        mutableStateOf(false)
    }
    var showGpsDialog by remember {
        mutableStateOf(false)
    }

    val multiplePermission = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val gpsStatusReceiver = GpsStatusReceiver()

    LaunchedEffect(context) {
        gpsTracker.value = isGpsEnabled
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(gpsStatusReceiver, filter)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { true }) {
            requestGps = true
        }
    }

    LaunchedEffect(multiplePermission.permissions) {
        if (!multiplePermission.permissions.all { it.status.isGranted }) {
            requestPermissionLauncher.launch(multiplePermission.permissions.map { it.permission }
                .toTypedArray())
        } else {
            requestGps = true
        }
    }






    LaunchedEffect(requestGps) {
        if (requestGps) {
            gpsTracker.collectLatest { gpsEnable ->
                gpsEnable?.let {
                    if (!it) {
                        showGpsDialog = true
                    }
                }
            }
        }
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var quranList by rememberSaveable {
        mutableStateOf(suwarList)
    }
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    var quranListcopy by rememberSaveable {
        mutableStateOf(suwarList)
    }

    LaunchedEffect(Unit) {
        state.collect { state ->
            when (state) {
                is QuranState.Error -> {
                    loading = false
                }

                QuranState.Ideal -> {}
                QuranState.Loading -> {
                    loading = true
                }

                is QuranState.Success -> {
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
        showGpsDialog = showGpsDialog,
        onDialogDismiss = {
            showGpsDialog = false
        },
        onConfirmClick = {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            activity.startActivity(intent)
            showGpsDialog = false
        },
        onItemClick = {
            navController.navigateToQuranDetailsScreen(it)
        },
        onSearchValueChanged = { value ->
            searchValue = value

            if (value.isNotEmpty()) {
                val searchedList = quranListcopy.filter {
                    it.name?.contains(value) ?: false
                }
                quranList = searchedList
            } else {
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
    showGpsDialog: Boolean,
    onDialogDismiss: () -> Unit,
    onConfirmClick: () -> Unit,
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    onItemClick: (Int) -> Unit
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
                            SuraItem(it, modifier = Modifier.animateItemPlacement(), onItemClick = {
                                onItemClick(it)
                            })
                        }
                    }
                }
            }
        }
    }
    if (showGpsDialog) {
        AlertDialog(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.location_ic),
                    contentDescription = null,
                    tint = Gold,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.pray_time_dialog_title),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.pray_time_dialog_text),
                    textAlign = TextAlign.Center
                )
            },
            dismissButton = {
                TextButton(onClick = {
                    onDialogDismiss()
                }) {
                    Text(
                        text = stringResource(R.string.cancel_btn_text),
                        color = Gold
                    )
                }
            },
            onDismissRequest = {
                onDialogDismiss()
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmClick()
                }) {
                    Text(
                        text = stringResource(R.string.enable_location_btn_text),
                        color = Gold
                    )
                }
            })
    }
}








