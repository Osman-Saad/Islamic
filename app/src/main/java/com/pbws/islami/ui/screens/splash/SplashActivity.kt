package com.pbws.islami.ui.screens.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pbws.islami.MainActivity
import com.pbws.islami.constant.Constant
import com.pbws.islami.ui.screens.onboarding.OnBoardingActivity
import com.pbws.islami.ui.screens.quran.QuranIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    val viewModel: SplashViewModel by viewModels()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPref = getSharedPreferences(Constant.APP_SETTINGS, MODE_PRIVATE)
            val onBoardingStatus = sharedPref.getBoolean(Constant.ON_BOARDING_STATUS, false)

            LaunchedEffect(key1 = true) {
                viewModel.channel.send(SplashIntent.GetSuwar)
                viewModel.state.collect { state ->
                    when (state) {
                        is SplashState.Error -> {}
                        is SplashState.Ideal -> {}
                        is SplashState.Loading -> {}
                        is SplashState.Success -> {
                            suwarList = state.quranItems
                            if (onBoardingStatus) {
                                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                                startActivity(intent);
                            } else {
                                val intent =
                                    Intent(this@SplashActivity, OnBoardingActivity::class.java)
                                startActivity(intent);
                            }

                            finish()
                        }
                    }
                }
            }
            SplashScreen()
        }
    }
}


