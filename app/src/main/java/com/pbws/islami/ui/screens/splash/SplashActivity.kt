package com.pbws.islami.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.pbws.islami.MainActivity
import com.pbws.islami.ui.screens.commoncomposable.Constant
import com.pbws.islami.ui.screens.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPref = getSharedPreferences(Constant.APP_SETTINGS, MODE_PRIVATE)
            val onBoardingStatus = sharedPref.getBoolean(Constant.ON_BOARDING_STATUS, false)
            LaunchedEffect(key1 = true) {
                delay(3000L)
                if(onBoardingStatus) {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent);
                }else{
                    val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                    startActivity(intent);
                }

                finish()
            }
            SplashScreen()
        }
    }
}


