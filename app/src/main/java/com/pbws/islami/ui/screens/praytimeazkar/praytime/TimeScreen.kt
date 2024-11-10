package com.pbws.islami.ui.screens.praytimeazkar.praytime

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.pbws.domain.entity.praytimeentity.PrayTime
import com.pbws.islami.R
import com.pbws.islami.model.Pray
import com.pbws.islami.ui.screens.commoncomposable.CarouselCard
import com.pbws.islami.ui.screens.commoncomposable.IslamicText
import com.pbws.islami.ui.screens.navigation.navigateToAzkarDetailsScreen
import com.pbws.islami.ui.screens.praytimeazkar.azkar.AzkarType
import com.pbws.islami.ui.screens.praytimeazkar.composable.AzkarCard
import com.pbws.islami.ui.screens.praytimeazkar.composable.PrayTimeItem
import com.pbws.islami.ui.screens.praytimeazkar.composable.PrayTimeText
import com.pbws.islami.ui.screens.praytimeazkar.initialPrayTime
import com.pbws.islami.ui.screens.quran.QuranIntent
import com.pbws.islami.ui.theme.AlphaBlack
import com.pbws.islami.ui.theme.Black
import com.pbws.islami.ui.theme.Brown
import com.pbws.islami.ui.theme.Gold
import com.pbws.islami.utils.convertToArabicNumerals
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TimeScreen(navController: NavController, viewModel: PrayTimeViewModel = hiltViewModel()) {
    var state = viewModel.state
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val activity = context as Activity
    var loading by remember {
        mutableStateOf(false)
    }
    var prayTime: PrayTime? by remember {
        mutableStateOf(null)
    }
    var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationManager = getSystemService(activity!!, LocationManager::class.java)
    var isGpsEnabled by remember {
        mutableStateOf(locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true)
    }
    val multiplePermission = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    @SuppressLint("MissingPermission")
    fun getInitialPrayTime() {
        if (multiplePermission.allPermissionsGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                Log.d("DS", "GRT")
                if (location != null) {
                    Log.d("DS", "GRT2")
                    // Use Geocoder to get city name
                    val geocoder = Geocoder(context)
                    val addresses =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    addresses?.let { addresses ->
                        if (addresses.isNotEmpty()) {
                            val cityName = addresses[0].adminArea
                            // Use cityName for your API request
                            coroutineScope.launch {
                                viewModel.channel.send(
                                    PrayTimeIntent.GetPrayTime(
                                        cityName.lowercase().replace(" governorate", "")
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (multiplePermission.allPermissionsGranted && isGpsEnabled) {
            getInitialPrayTime()
        } else {
            viewModel.channel.send(PrayTimeIntent.GetPrayTime("cairo"))
        }
    }

    LaunchedEffect(Unit) {
        state.collect { state ->
            when (state) {
                is PrayTimeState.Error -> {
                    loading = false
                }

                is PrayTimeState.Ideal -> {}
                is PrayTimeState.Loading -> {
                    loading = true
                }

                is PrayTimeState.Success -> {
                    loading = false
                    prayTime = state.prayTime
                }
            }
        }
    }
    TimeScreenContent(
        loading = loading,
        prayTime = prayTime,
        onAzkarCardClick = {
            navController.navigateToAzkarDetailsScreen(it)
        }
    )
}


@Composable
fun TimeScreenContent(
    loading: Boolean,
    prayTime: PrayTime?,
    onAzkarCardClick: (String) -> Unit
) {
    val date = prayTime?.data?.date
    val gregorianDate =
        "${date?.gregorian?.day}-${date?.gregorian?.month?.number}\n${date?.gregorian?.year}"
    val hijriDate = "${date?.hijri?.day}-${date?.hijri?.month?.number}\n${date?.hijri?.year}"
    val prayData = prayTime?.data?.timings
    var prayList = listOf<Pray>()
    if (prayData != null) {
        prayList = listOf(
            Pray(prayTime = prayData?.fajr!!, prayNameEn = "Fajr", prayNameAr = "الفجر"),
            Pray(prayTime = prayData?.sunrise!!, prayNameEn = "Sunrise", prayNameAr = "الشروق"),
            Pray(prayTime = prayData?.dhuhr!!, prayNameEn = "Dhuhr", prayNameAr = "الظهر"),
            Pray(prayTime = prayData?.asr!!, prayNameEn = "Asr", prayNameAr = "العصر"),
            Pray(prayTime = prayData?.sunset!!, prayNameEn = "Sunset", prayNameAr = "الغروب"),
            Pray(prayTime = prayData?.maghrib!!, prayNameEn = "Maghrib", prayNameAr = "المغرب"),
            Pray(prayTime = prayData?.isha!!, prayNameEn = "Isha", prayNameAr = "العشاء"),
            Pray(prayTime = prayData?.imsak!!, prayNameEn = "Imsak", prayNameAr = "الإمساك"),
            Pray(
                prayTime = prayData?.midnight!!,
                prayNameEn = "Midnight",
                prayNameAr = "منتصف الليل"
            ),
            Pray(
                prayTime = prayData?.firstthird!!,
                prayNameEn = "Firstthird",
                prayNameAr = "الثلث الأولل"
            ),
            Pray(
                prayTime = prayData?.lastthird!!,
                prayNameEn = "Lastthird",
                prayNameAr = "الثلث الأخير"
            ),
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.time_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        IslamicText()
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(color = Brown, shape = RoundedCornerShape(50.dp))
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.time_card_shape),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            if(loading){
                CircularProgressIndicator(color = AlphaBlack, modifier = Modifier.align(Alignment.Center))
            }else{
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!loading) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            PrayTimeText(
                                text = convertToArabicNumerals(gregorianDate),
                                color = Color.White,
                                fontSize = 16,
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                PrayTimeText(
                                    text = stringResource(R.string.pray_time_text),
                                    color = Brown,
                                    fontSize = 20
                                )
                                PrayTimeText(
                                    text = date?.hijri?.weekday?.ar ?: "",
                                    color = Black,
                                    fontSize = 18
                                )
                            }
                            PrayTimeText(
                                text = convertToArabicNumerals(hijriDate),
                                color = Color.White,
                                fontSize = 16
                            )

                        }
                    }
                    Box(modifier = Modifier.wrapContentHeight()) {
                        if(!loading){
                            CarouselCard(pagesCount = prayList.size) { page, pagerState ->
                                PrayTimeItem(
                                    prayTime = prayList[page].prayTime,
                                    prayNameAr = prayList[page].prayNameAr,
                                    prayNameEn = prayList[page].prayNameEn,
                                    page = page,
                                    pagerState = pagerState
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrayTimeText(
                text = stringResource(R.string.azkar_en),
                color = Color.White,
                fontSize = 16
            )

            PrayTimeText(
                text = stringResource(R.string.azkar_ar),
                color = Color.White,
                fontSize = 16
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AzkarCard(
                    image = R.drawable.mornning_azkar,
                    title = stringResource(R.string.azkar_sabah),
                    onClick = {
                        onAzkarCardClick(AzkarType.AZKAR_ELSABAH.name)
                    })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                AzkarCard(
                    image = R.drawable.evening_azkar,
                    title = stringResource(R.string.evenening_azkar),
                    onClick = {
                        onAzkarCardClick(AzkarType.AZKAR_ELMASAA.name)
                    })
            }
        }

    }

}


